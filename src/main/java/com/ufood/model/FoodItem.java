package com.ufood.model;

import com.ufood.db.Documentable;
import org.bson.Document;

import java.util.ArrayList;

import static com.ufood.db.Constants.*;
import static com.ufood.db.DBDriver.*;


public class FoodItem implements Documentable {
    private String name;
    private double calories;//on 100 gramm
    private double protein;
    private double carbohydrate;
    private double fat;
    private double quantity = 1d;
    private ArrayList imagesURL;

    public ArrayList getImagesURL() {
        return imagesURL;
    }

    public void setImagesURL(ArrayList imagesURL) {
        this.imagesURL = new ArrayList(imagesURL);
    }

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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public FoodItem (Document bson) {
        name = bson.get("name").toString();
        calories = new Double(bson.get("calories").toString());
        protein = new Double(bson.get("proteins").toString());
        carbohydrate = new Double(bson.get("carbohydrates").toString());
        fat = new Double(bson.get("fats").toString());
        imagesURL = (ArrayList)bson.get("imagesURL");
    }

    public FoodItem() {

    }

    public static FoodItem getFoodItemByName(String name) {
        FoodItem foodItem = null;
        Document document = getDBDriver().select(FOOD_COLLECTION, name);
        if (document != null)
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
                .append("fats", this.fat)
                .append("quantity", this.quantity)
                .append("imagesURL", this.imagesURL!=null?this.imagesURL:new ArrayList());
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
