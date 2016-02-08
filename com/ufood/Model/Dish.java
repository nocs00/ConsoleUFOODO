package com.ufood.Model;

import com.ufood.DB.Documentable;
import org.bson.Document;
import java.util.*;
import static com.ufood.DB.Constants.*;
import static com.ufood.DB.DBDriver.*;

public class Dish extends FoodItem implements Documentable {
    private String name;
    private HashSet<FoodItem> foodItems;
    private double calories;//on 100 gramm
    private double protein;
    private double carbohydrate;
    private double fat;

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(HashSet<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    @Override
    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public void setProtein(double protein) {
        this.protein = protein;
    }

    @Override
    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    @Override
    public void setFat(double fat) {
        this.fat = fat;
    }

    public Dish(String name) {
        this.calories = 0;
        this.protein = 0;
        this.carbohydrate = 0;
        this.fat = 0;

        loadItems(name);
    }

    public Dish() {
        
    }

    public static Dish getDishByName(String name) {
        Dish dish = null;
        dish = new Dish(name);
        return dish;
    }

    public static ArrayList<Dish> findDishesByFoodItems(ArrayList<String> foodItems) {
        ArrayList<Dish> possibleDishes = new ArrayList<Dish>();

        for (Document dishDocument : getDBDriver().selectAll(DISH_COLLECTION)) {
            boolean containAll = true;
            for (Object food : (Iterable)dishDocument.get(FOOD_COLLECTION)) { //TODO: why choose apple salad
                if (food instanceof String || food instanceof Document ) {
                    if (food instanceof Document)
                        food = (String)((Document)food).get("name");
                    boolean contains = false;
                    for (String f : foodItems) {
                        if (f == null)
                            break;
                        if (f.equals(food)) {
                            contains = true;
                            break;
                        }
                    }
                    if (contains) continue;
                    containAll = false;
                    break;
                }
            }
            if (containAll) possibleDishes.add(new Dish(dishDocument.getString("name")));
        }

        return possibleDishes;
    }

    private void loadItems(String dish) {
        this.name = dish;
        this.foodItems = new HashSet<FoodItem>();
        Document dishDocument = getDBDriver().select(DISH_COLLECTION, name);//get first entry in dishes with name (Document)
        ArrayList foods = (ArrayList)dishDocument.get(FOOD_COLLECTION);
        FoodItem food = null;

        for (int i = 0; i < foods.size(); i++) {
            food = null;

            Object t;
            if (foods.get(i) instanceof String)
                t = foods.get(i);
            else
                t = ((Document)foods.get(i)).get("name");

            food = FoodItem.getFoodItemByName((String)t);
            foodItems.add(food);
        }

        countNutrients(foodItems);
    }

    private void countNutrients(HashSet<FoodItem> foodItems) {
        for (FoodItem foodItem : foodItems) {
            FoodItem tmp = foodItem;
            Double quantity = foodItem.getQuantity();

            this.calories += tmp.getCalories()*quantity;
            this.protein += tmp.getProtein()*quantity;
            this.carbohydrate += tmp.getCarbohydrate()*quantity;
            this.fat += tmp.getFat()*quantity;
        }
        getDBDriver().update(DISH_COLLECTION, this.name, this.getDocument());
    }

    public String getName() {
        return name;
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

    @Override
    public Document getDocument() {
        ArrayList foods = new ArrayList();

        for (FoodItem food : this.foodItems) {
            foods.add(food.getDocument());
        }

        return new Document("name",this.name)
                .append("calories", this.calories)
                .append("proteins", this.protein)
                .append("carbohydrates", this.carbohydrate)
                .append("fats", this.fat)
                .append(FOOD_COLLECTION, foods);
    }
}
