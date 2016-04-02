package com.ufood.schema;

import com.ufood.db.Constants;
import com.ufood.exception.SchemaNotFoundException;
import org.bson.Document;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ufood.db.DBDriver.getDBDriver;

/**
 * Created by pdudenkov on 01.04.2016.
 */
public class SchemaValidator {
    private final static Logger LOG = Logger.getLogger(SchemaValidator.class.getSimpleName());

    private static boolean isValidModel(Document modelDocument, Document schema) {
        for (Map.Entry<String, Object> entry : modelDocument.entrySet()) {
            final String name = entry.getKey();
            final Class clazz = entry.getValue().getClass();

            if (name.equals("_id")) continue;

            if (!schema.containsKey(name)) return false;
            else {
                final Class c = schema.get(name).getClass();
                if (!clazz.equals(c)) return false;
            }
        }

        return true;
    }

    public static Document checkAndApplySchema(String fromCollection, Document modelDocument) throws InstantiationException, IllegalAccessException {
        if (!SchemaStorage.isValidationEnabled()) {
            LOG.log(Level.INFO, String.format("Schema validation disabled. {%s : %s}",
                    fromCollection, modelDocument.get("name").toString()));
            return modelDocument;
        }

        Class modelClass = Constants.mapClassByDatabaseColletion.get(fromCollection);
        Document schema = null;
        try {
            schema = SchemaStorage.getSchemaByClass(modelClass);
        } catch (SchemaNotFoundException e) {
            LOG.log(Level.INFO, String.format("Schema not found for {%s : %s}",
                    fromCollection, modelDocument.get("name").toString()));
            return modelDocument;
        }

        if (isValidModel(modelDocument, schema)) return modelDocument;
        Document newModelDocument = new Document();

        for (Map.Entry<String, Object> schemaEntry : schema.entrySet()) {
            final String schemaEntryName = schemaEntry.getKey();
            final Class schemaEntryClass = schemaEntry.getValue().getClass();

            boolean contain = false;

            for (Map.Entry<String, Object> modelEntry : modelDocument.entrySet()) {
                final String modelEntryName = modelEntry.getKey();
                final Class modelEntryClass = modelEntry.getValue().getClass();
                if (modelEntryName.equals("_id")) {newModelDocument.append("_id", modelEntry.getValue()); continue;}

                if (modelEntryName.equals(schemaEntryName) && modelEntryClass.equals(schemaEntryClass)) {
                    contain = true;
                    newModelDocument.append(modelEntryName, modelEntry.getValue());
                    break;
                }
            }
            if (!contain) {
                if (schemaEntryClass.equals(Double.class))
                    newModelDocument.append(schemaEntryName, new Double(0));
                else
                    newModelDocument.append(schemaEntryName,schemaEntryClass.newInstance());
            }
        }

        //TODO: enable after debug
        //getDBDriver().update(fromCollection, modelDocument.get("name").toString(), newModelDocument);
        return newModelDocument;
    }
}
