package com.ufood.schema;


import com.ufood.exception.SchemaNotFoundException;
import com.ufood.util.Engine;
import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ufood.db.Constants.*;

/**
 * Created by pdudenkov on 23.03.2016.
 */
public abstract class SchemaStorage {
    private static boolean isValidationEnabled = false;
    private final static Logger LOG = Logger.getLogger(SchemaStorage.class.getSimpleName());
    private static Map<String, Document> schemas = new HashMap<>();
    private static String errorMessage = "";

    static {
        if (containSchemaFiles()) {
            try {
                schemas = getSchemaMap(getSchemaFiles());
                schemas = injectSchemasInMap(schemas);
                isValidationEnabled = true;
            } catch (IOException|SchemaNotFoundException e) {
                LOG.log(Level.SEVERE, String.format("Unable to load schema files: {%s}. Schema validation disabled.", errorMessage));
            }
        }
    }

    public static boolean isValidationEnabled() {
        return isValidationEnabled;
    }

    public static Document getSchemaByClass(Class clazz) throws SchemaNotFoundException {
        final String name = clazz.getSimpleName().toLowerCase();
        return getSchemaByName(name);
    }

    public static Document getSchemaByName(String name) throws SchemaNotFoundException {
        if (!isValidationEnabled) throw new SchemaNotFoundException();

        for (Map.Entry<String, Document> entry : schemas.entrySet()) {
            final String fileName = entry.getKey().toLowerCase();
            if (fileName.contains(name)) {
                return entry.getValue();
            }
        }

        throw new SchemaNotFoundException();
    }

    private static boolean containSchemaFiles() {
        File folder = new File(RESOURCES_PATH);
        File[] fileList = folder.listFiles();
        for (File file : fileList) {
            if (file.getName().toLowerCase().contains("_schema.json")) return true;
        }
        return false;
    }

    private static List<File> getSchemaFiles() {
        List<File> list = new ArrayList<>();
        File folder = new File(RESOURCES_PATH);
        File[] fileList = folder.listFiles();
        for (File file : fileList) {
            if (file.getName().toLowerCase().contains("_schema.json")) {
                list.add(file);
            }
        }
        return list;
    }

    private static Map<String, Document> getSchemaMap(List<File> schemaFiles) throws IOException {
        Map<String, Document> schemas = new HashMap<>();

        for (File file : schemaFiles) {
            final String name = file.getName();
            errorMessage = name;
            final Document schema = Document.parse(Engine.readJsonFile(RESOURCES_PATH + name));
            schemas.put(name, schema);
        }

        return schemas;
    }

    private static Map<String, Document> injectSchemasInMap(Map<String, Document> schemasIn) throws SchemaNotFoundException {
        Map<String, Document> schemasOut = new HashMap<>();

        for (Map.Entry<String, Document> entry : schemasIn.entrySet()) {
            final String name = entry.getKey();
            final Document documentToInspect = entry.getValue();
            final Document documentToInsert = injectSchemasInDocument(documentToInspect);
            schemasOut.put(name, documentToInsert);
        }

        return schemasOut;
    }

    private static Document injectSchemasInDocument(Document document) throws SchemaNotFoundException {
        final Document documentResult = new Document();

        for (Map.Entry<String, Object> entry : document.entrySet()) {
            final Class clazz = entry.getValue().getClass();
            Object object = entry.getValue();
            if (clazz == String.class && ((String)object).contains("_schema.json")) {
                errorMessage = (String)object;
                object = schemas.get(object);
                if (object == null) throw new SchemaNotFoundException();
            }
            documentResult.append(entry.getKey(), object);
        }
        return documentResult;
    }
}
