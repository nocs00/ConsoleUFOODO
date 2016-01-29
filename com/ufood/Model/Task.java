package com.ufood.Model;

import com.ufood.DB.Documentable;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Date;
import static com.ufood.DB.DBDriver.*;
import static com.ufood.DB.Constants.*;

public class Task implements Documentable {
    private String userID;
    private SEX sex;
    private ArrayList<String> foodItems;

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
