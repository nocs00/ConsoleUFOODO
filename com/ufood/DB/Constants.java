package com.ufood.DB;

import java.util.ResourceBundle;

public abstract class Constants {
    public final static String RESOURCE_PATH = "com.ufood.resources.";
    private final static ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH+"constants");

//    public final static String DATABASE_NAME = "test";
//    public final static String FOOD_COLLECTION = "food_items";
//    public final static String DISH_COLLECTION = "dishes";
//    public final static String MENU_COLLECTION = "menus";
//    public final static String TASK_COLLECTION = "tasks";
//    public final static String RESULT_COLLECTION = "results";
//    public final static String USER_ID = "userID";
//    public final static String IMAGE_PATH_DEBUG = "C:/Users/pdudenkov/files/";
//    public final static String IMAGE_PATH ="/var/lib/tomcat7/webapps/ROOT/files";
//    public final static String IMAGE_BASE_URL = "ufoodo.com/files/";

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

    public static enum SEX {MALE, FEMALE};
    public static enum ACTIVITY_LEVEL {SEDENTARY, LIGHT, MODERATE, HIGH, EXTRA_HIGH};
    public static enum BODY_TYPE {FAT, NORMAL, ATHLETE, THIN};

    /**********Multipliers for BMR (Basal Metabolic Rate)
     ** Sedentary - little or no excercise      - 1.2
     ** Light active - sports 1-3 times/week    - 1.4
     ** Moderate active - sports 3-5 times/week - 1.55
     ** Very active - sports 6-7 times/week     - 1.7
     ** Extra active - sports 2 times per day   - 1.9   */
}
