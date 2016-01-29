package com.ufood.Model;

import com.ufood.DB.Documentable;
import org.bson.Document;
import static com.ufood.DB.Constants.*;
import static com.ufood.DB.DBDriver.*;


public class FoodItem implements Documentable {
    private String name;
    private double calories;//on 100 gramm
    private double protein;
    private double carbohydrate;
    private double fat;

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public FoodItem (Document bson) {
        name = bson.get("name").toString();
        calories = new Double(bson.get("calories").toString());
        protein = new Double(bson.get("proteins").toString());
        carbohydrate = new Double(bson.get("carbohydrates").toString());
        fat = new Double(bson.get("fats").toString());
    }

    protected FoodItem() {

    }

    public static FoodItem getFoodItemByName(String name) {
        FoodItem foodItem = null;
        Document document = getDBDriver().select(FOOD_COLLECTION, name);
        foodItem  = new FoodItem(document);
        return foodItem;
    }

    public String getName() {
        return name;
    }

    @Override
    public Document getDocument() {
        return new Document("name", this.name)
                .append("calories", this.calories)
                .append("proteins", this.protein)
                .append("carbohydrates", this.carbohydrate)
                .append("fats", this.fat);
    }

    public double getFat() {
        return fat;
    }

    public double getCarbohydrate() {

        return carbohydrate;
    }

    public double getProtein() {

        return protein;
    }

    public double getCalories() {

        return calories;
    }
}
