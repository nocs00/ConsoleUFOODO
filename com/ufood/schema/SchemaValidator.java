package com.ufood.schema;

import com.ufood.DB.Constants;
import org.bson.Document;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ufood.DB.DBDriver.getDBDriver;

/**
 * Created by pdudenkov on 01.04.2016.
 */
public class SchemaValidator {
    private final static Map<String, Class> currentSchema;

    static {
        currentSchema = new LinkedHashMap<>();
        final Class stringClass = String.class;
        final Class doubleClass = Double.class;
        final Class arrayListClass = ArrayList.class;

        currentSchema.put("_id", null);
        currentSchema.put("name", stringClass);
        currentSchema.put("calories", doubleClass);
        currentSchema.put("proteins", doubleClass);
        currentSchema.put("carbohydrates", doubleClass);
        currentSchema.put("fats", doubleClass);
        currentSchema.put("quantity",doubleClass);
        currentSchema.put("imagesURL", arrayListClass);
    }

    private static boolean isValidFoodItemSchema(Document foodItemDocument) {
        for (Map.Entry<String, Object> foodItemSchemaEntry : foodItemDocument.entrySet()) {
            final String schemaEntryName = foodItemSchemaEntry.getKey();
            final Class schemaEntryValueClass = foodItemSchemaEntry.getValue().getClass();

            if (schemaEntryName.equals("_id")) continue;

            if (!currentSchema.containsKey(schemaEntryName)) return false;
            else {
                final Class c = currentSchema.get(schemaEntryName);
                if (!schemaEntryValueClass.equals(c)) return false;
            }
        }

        return true;
    }

    public static Document checkAndApplySchema(Document foodItemDocument) throws InstantiationException, IllegalAccessException {
        if (isValidFoodItemSchema(foodItemDocument)) return foodItemDocument;
        Document newFoodItemDocument = new Document();

        for (Map.Entry<String, Class> currentSchemaEntry : currentSchema.entrySet()) {
            final String currentSchemaEntryName = currentSchemaEntry.getKey();
            final Class currentSchemaEntryValueClass = currentSchemaEntry.getValue();

            if (currentSchemaEntryName.equals("_id")) {newFoodItemDocument.append("_id", foodItemDocument.get("_id")); continue;}
            boolean contain = false;

            for (Map.Entry<String, Object> foodItemSchemaEntry : foodItemDocument.entrySet()) {
                final String schemaEntryName = foodItemSchemaEntry.getKey();
                final Class schemaEntryClass = foodItemSchemaEntry.getValue().getClass();

                if (currentSchemaEntryName.equals(schemaEntryName) && currentSchemaEntryValueClass.equals(schemaEntryClass)) {
                    contain = true;
                    newFoodItemDocument.append(schemaEntryName, foodItemSchemaEntry.getValue());
                    break;
                }
            }
            if (!contain) {
                if (currentSchemaEntryValueClass.equals(Double.class))
                    newFoodItemDocument.append(currentSchemaEntryName, new Double(0));
                else
                    newFoodItemDocument.append(currentSchemaEntryName,currentSchemaEntryValueClass.newInstance());
            }
        }

        //enable after debug
        getDBDriver().update(Constants.FOOD_COLLECTION, foodItemDocument.get("name").toString(), newFoodItemDocument);
        return newFoodItemDocument;
    }
}
