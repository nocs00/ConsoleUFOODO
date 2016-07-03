package com.ufood.db;

import com.ufood.model.*;
import com.ufood.properties.MyProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

public abstract class Constants {
    public final static String RESOURCES_BUNDLE_PATH = "com.ufood.resources.";
    private final static Properties props = MyProperties.getProps();

    public final static String DB_NAME =      props.getProperty("database.name");
    public final static String FOOD_COLLECTION =    props.getProperty("food.collection");
    public final static String DISH_COLLECTION =    props.getProperty("dish.collection");
    public final static String MENU_COLLECTION =    props.getProperty("menu.collection");
    public final static String TASK_COLLECTION =    props.getProperty("task.collection");
    public final static String RESULT_COLLECTION =  props.getProperty("result.collection");
    public final static String USER_ID =            props.getProperty("user.id");
    public final static String IMAGE_PATH_DEBUG =   props.getProperty("image.path.debug");
    public final static String IMAGE_PATH =         props.getProperty("image.path");
    public final static String IMAGE_BASE_URL =     props.getProperty("image.base.url");
    public final static String RESOURCES_PATH =     props.getProperty("relative.path.to.resources");

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
