package com.ufood.model;

import com.ufood.db.dao.mongodb.AbstractEntity;
import org.mongodb.morphia.annotations.Entity;

import java.util.List;

@Entity
public class Dish extends AbstractEntity {
    private String name;

    private double calories;//on 100 gramm
    private double protein;
    private double carbohydrate;
    private double fat;

    private List<FoodItem> foodItems;
    private List imageURLs;

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

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public List getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(List imageURLs) {
        this.imageURLs = imageURLs;
    }
}
