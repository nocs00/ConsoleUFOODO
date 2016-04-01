package com.ufood.schema;


import com.ufood.DB.Constants;
import org.bson.Document;

import javax.print.Doc;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ufood.DB.DBDriver.*;
import static com.ufood.util.Engine.readJsonFile;
import static com.ufood.DB.Constants.*;

/**
 * Created by pdudenkov on 23.03.2016.
 */
public abstract class SchemaStorage {
    static Boolean isValidationEnabled = true;
    private final static Logger LOG = Logger.getLogger(SchemaStorage.class.getSimpleName());

    final static Document imageurl_schema;
    final static Document task_schema;
    final static Document fooditem_schema;
    final static Document dish_schema;
    final static Document menu_schema;
    final static Document result_schema;

    static {
        String[] jsonsWithSchemas = new String[6];
        String schemaName = "";
        try {
            schemaName = SCHEMA_FILENAME_IMAGEURL;
            jsonsWithSchemas[0] = readJsonFile(RESOURCES_PATH + schemaName);

            schemaName = SCHEMA_FILENAME_TASK;
            jsonsWithSchemas[1] = readJsonFile(RESOURCES_PATH + schemaName);

            schemaName = SCHEMA_FILENAME_FOODITEM;
            jsonsWithSchemas[2] = readJsonFile(RESOURCES_PATH + schemaName);

            schemaName = SCHEMA_FILENAME_DISH;
            jsonsWithSchemas[3] = readJsonFile(RESOURCES_PATH + schemaName);

            schemaName = SCHEMA_FILENAME_MENU;
            jsonsWithSchemas[4] = readJsonFile(RESOURCES_PATH + schemaName);

            schemaName = SCHEMA_FILENAME_RESULT;
            jsonsWithSchemas[5] = readJsonFile(RESOURCES_PATH + schemaName);

        } catch (IOException e) {
            LOG.log(Level.SEVERE, String.format("Failed to read %s file. Wrong path, not found or corrupted. Schema validation will be disabled.", schemaName));
            isValidationEnabled = false;
        }

        if (isValidationEnabled) {
            imageurl_schema = injectSchemas(Document.parse(jsonsWithSchemas[0]));
            task_schema = injectSchemas(Document.parse(jsonsWithSchemas[1]));
            fooditem_schema = injectSchemas(Document.parse(jsonsWithSchemas[2]));
            dish_schema = injectSchemas(Document.parse(jsonsWithSchemas[3]));
            menu_schema = injectSchemas(Document.parse(jsonsWithSchemas[4]));
            result_schema = injectSchemas(Document.parse(jsonsWithSchemas[5]));
        } else {
            dish_schema = null;
            fooditem_schema = null;
            imageurl_schema = null;
            menu_schema = null;
            result_schema = null;
            task_schema = null;
        }
    }
//TODO : this fucking method...
//    private static Document injectSchemas(Document _schema) {
//        Map<String, Object> mapToInsert = new HashMap<>();
//
//        for (Iterator<Map.Entry<String, Object>> i = _schema.entrySet().iterator(); i.hasNext();) {
//            Map.Entry<String, Object> entry = i.next();
//            if (entry.getValue().getClass() == String.class &&
//                    ((String)entry.getValue()).contains("_schema.json")) {
//                String schemaName = (String)entry.getValue();
//                if (schemaName.equals(SCHEMA_FILENAME_IMAGEURL)) {
//                    mapToInsert.put(entry.getKey(), imageurl_schema);
//                }
//                i.remove();
//            }
//        }
//
//        _schema.entrySet().addAll(mapToInsert);
//
//        return _schema;
//    }
}
