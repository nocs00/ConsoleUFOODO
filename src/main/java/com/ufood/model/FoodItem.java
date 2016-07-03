package com.ufood.model;

import com.ufood.db.dao.mongodb.AbstractEntity;
import org.mongodb.morphia.annotations.Entity;

import java.util.List;

@Entity
public class FoodItem extends AbstractEntity {
    private String name;

    private double calories;//on 100 gramm
    private double protein;
    private double carbohydrate;
    private double fat;
    private double quantity = 1d;

    private List imagesURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getFat() {
        return fat;
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

    public List getImagesURL() {
        return imagesURL;
    }

    public void setImagesURL(List imagesURL) {
        this.imagesURL = imagesURL;
    }
}
