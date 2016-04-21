package com.ufood.db;

import com.ufood.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class Constants {
    public final static String RESOURCES_BUNDLE_PATH = "com.ufood.resources.";
    private final static ResourceBundle res = ResourceBundle.getBundle(RESOURCES_BUNDLE_PATH+"constants");

    public final static String DATABASE_NAME =      res.getString("database.name");
    public final static String FOOD_COLLECTION =    res.getString("food.collection");
    public final static String DISH_COLLECTION =    res.getString("dish.collection");
    public final static String MENU_COLLECTION =    res.getString("menu.collection");
    public final static String TASK_COLLECTION =    res.getString("task.collection");
    public final static String RESULT_COLLECTION =  res.getString("result.collection");
    public final static String USER_ID =            res.getString("user.id");
    public final static String IMAGE_PATH_DEBUG =   res.getString("image.path.debug");
    public final static String IMAGE_PATH =         res.getString("image.path");
    public final static String IMAGE_BASE_URL =     res.getString("image.base.url");
    public final static String RESOURCES_PATH =     res.getString("relative.path.to.resources");

    public final static String SCHEMA_FILENAME_FOODITEM =   res.getString("schema.file.name.fooditem");
    public final static String SCHEMA_FILENAME_DISH =       res.getString("schema.file.name.dish");
    public final static String SCHEMA_FILENAME_TASK =       res.getString("schema.file.name.task");
    public final static String SCHEMA_FILENAME_RESULT =     res.getString("schema.file.name.result");
    public final static String SCHEMA_FILENAME_IMAGEURL =   res.getString("schema.file.name.imageurl");
    public final static String SCHEMA_FILENAME_MENU =       res.getString("schema.file.name.menu");

    public static enum SEX {MALE, FEMALE};
    public static enum ACTIVITY_LEVEL {SEDENTARY, LIGHT, MODERATE, HIGH, EXTRA_HIGH};
    public static enum BODY_TYPE {FAT, NORMAL, ATHLETE, THIN};

    public final static Map<String, Class> mapClassByDatabaseColletion;

    static {
        mapClassByDatabaseColletion = new HashMap<>();
        mapClassByDatabaseColletion.put(FOOD_COLLECTION, FoodItem.class);
        mapClassByDatabaseColletion.put(DISH_COLLECTION, Dish.class);
        mapClassByDatabaseColletion.put(MENU_COLLECTION, Menu.class);
        mapClassByDatabaseColletion.put(TASK_COLLECTION, Task.class);
        mapClassByDatabaseColletion.put(RESULT_COLLECTION, Result.class);
    }

    /**********Multipliers for BMR (Basal Metabolic Rate)
     ** Sedentary - little or no excercise      - 1.2
     ** Light active - sports 1-3 times/week    - 1.4
     ** Moderate active - sports 3-5 times/week - 1.55
     ** Very active - sports 6-7 times/week     - 1.7
     ** Extra active - sports 2 times per day   - 1.9   */
}
