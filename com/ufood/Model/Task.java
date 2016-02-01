package com.ufood.Model;

import com.ufood.DB.Constants;
import com.ufood.DB.Documentable;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Date;
import static com.ufood.DB.DBDriver.*;
import static com.ufood.DB.Constants.*;

public class Task implements Documentable {
    private String userID;
    private SEX sex = SEX.MALE;
    private int age = 25;
    private double height = 175d;
    private double weight = 75d;
    private ACTIVITY_LEVEL activity_level = ACTIVITY_LEVEL.LIGHT;
    private BODY_TYPE body_type = BODY_TYPE.NORMAL;
    private ArrayList<String> foodItems;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ACTIVITY_LEVEL getActivity_level() {
        return activity_level;
    }

    public void setActivity_level(String activity_level) {
        switch (activity_level) {
            case "sedentary" :
                this.activity_level = ACTIVITY_LEVEL.SEDENTARY;
                break;
            case "light" :
                this.activity_level = ACTIVITY_LEVEL.LIGHT;
                break;
            case "moderate" :
                this.activity_level = ACTIVITY_LEVEL.MODERATE;
                break;
            case "high" :
                this.activity_level = ACTIVITY_LEVEL.HIGH;
                break;
            case "extra_high" :
                this.activity_level = ACTIVITY_LEVEL.EXTRA_HIGH;
                break;
            default:
                this.activity_level = ACTIVITY_LEVEL.LIGHT;
                break;
        }
    }

    public BODY_TYPE getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        switch (body_type) {
            case "fat" :
                this.body_type = BODY_TYPE.FAT;
                break;
            case "normal" :
                this.body_type = BODY_TYPE.NORMAL;
                break;
            case "athlete" :
                this.body_type = BODY_TYPE.ATHLETE;
                break;
            case "thin" :
                this.body_type = BODY_TYPE.THIN;
                break;
            default:
                this.body_type = BODY_TYPE.NORMAL;
                break;
        }
    }

    public static Task getTask() {
        Date now = new Date();
        Task task = new Task();
        Document taskDocument = getDBDriver().select(TASK_COLLECTION);
        if (taskDocument == null) return null;
        getDBDriver().delete(TASK_COLLECTION, taskDocument);

        task.userID = taskDocument.getString(USER_ID);

        if ("male".equals(taskDocument.getString("sex")))
            task.sex = SEX.MALE;
        else if ("female".equals(taskDocument.getString("sex")))
            task.sex = SEX.FEMALE;
        else
            task.sex = null;

        task.foodItems = new ArrayList<String>((ArrayList)taskDocument.get("food_items"));
        Date after = new Date();
        System.out.println("getTask:"+(after.getTime()-now.getTime()) + " ms");
        return task;
    }

    public String getUserID() {
        return userID;
    }

    public SEX getSex() {
        return sex;
    }

    public ArrayList<String> getFoodItems() {
        return foodItems;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setSex(String sex) {
        if (sex.equals("male") || sex.equals("m")) {
            this.sex = SEX.MALE;
        } else if (sex.equals("female") || sex.equals("f")) {
            this.sex = SEX.FEMALE;
        }
        if (this.sex == null)
            this.sex = SEX.MALE;
    }

    public void setFoodItems(ArrayList<String> foodItems) {
        this.foodItems = foodItems;
    }

    @Override
    public Document getDocument() {
        ArrayList foodItemsObject = new ArrayList(this.foodItems);
        return new Document()
                .append("properties", new Document().append("sex", this.sex==SEX.MALE?"male":"female"))
                .append("foodItems", foodItemsObject);
    }

    @Override
    public String toString() {
        return userID.toString()+sex+foodItems;
    }
}
