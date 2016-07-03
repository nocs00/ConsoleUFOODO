package com.ufood.model;

import com.ufood.db.dao.mongodb.AbstractEntity;
import org.mongodb.morphia.annotations.Entity;

import java.util.List;

@Entity
public class Menu extends AbstractEntity {
    private double calories;//on 100 gramm
    private double protein;
    private double carbohydrate;
    private double fat;

    private List<Dish> dishes;

    //todo link to user owner

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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
