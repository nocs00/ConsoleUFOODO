package com.ufood.DB;

public abstract class Constants {
    public final static String DATABASE_NAME = "test";
    public final static String FOOD_COLLECTION = "food_items";
    public final static String DISH_COLLECTION = "dishes";
    public final static String MENU_COLLECTION = "menus";
    public final static String TASK_COLLECTION = "tasks";
    public final static String RESULT_COLLECTION = "results";
    public final static String USER_ID = "user_id";
    public static enum SEX {MALE, FEMALE};
    public static enum ACTIVITY_LEVEL {SEDENTARY, LIGHT, MODERATE, HIGH, EXTRA_HIGH};

    /**********Multipliers for BMR (Basal Metabolic Rate)
     ** Sedentary - little or no excercise      - 1.2
     ** Light active - sports 1-3 times/week    - 1.4
     ** Moderate active - sports 3-5 times/week - 1.55
     ** Very active - sports 6-7 times/week     - 1.7
     ** Extra active - sports 2 times per day   - 1.9   */
}
