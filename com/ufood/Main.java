package com.ufood;

import com.ufood.API.Control;
import com.ufood.Model.Dish;
import com.ufood.Model.FoodItem;
import com.ufood.Model.Result;
import com.ufood.Model.Task;
import com.ufood.util.Engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by pdudenkov on 15.01.2016.
 */
public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        task.setUserID("masha90");
        task.setSex("female");
        task.setAge(18);
        task.setHeight(160d);
        task.setWeight(53);
        task.setActivity_level("light");
        task.setBody_type("normal");
        ArrayList foodItems = new ArrayList();
        Collections.addAll(foodItems, "apple", "potato", "chicken");
        //Collections.addAll(foodItems, "rice", null);
        task.setFoodItems(foodItems);

        Result result = Engine.getResult(task);
        //System.out.println(result.getDocument().toJson());




        //add food_item
        System.out.println("Add fooditem:");
        FoodItem test = new FoodItem();
        test.setName("atest");
        System.out.println(new Control().addFoodItem(test));
        System.out.println("\n\n\n");
        //add dish
        System.out.println("Add dish:");
        Dish dish = new Dish();
        dish.setName("TESTIARA");
        dish.setFoodItems(new HashMap<FoodItem, Double>());
        System.out.println(new Control().addDish(dish));
        System.out.println("\n\n\n");
        //get fooditems
        System.out.println("get all fooditems:");
        System.out.println(new Control().getFoodItems());
        System.out.println("\n\n\n");
        //get dishes
        System.out.println("Get all dishes:");
        System.out.println(new Control().getDishes());
        System.out.println("\n\n\n");
        //get dish by name:
        System.out.println("get dish by name:");
        System.out.println(new Control().getDishByName("soup"));
        System.out.println("\n\n\n");
        //absolute search
        System.out.println("absolute search:");
        System.out.println(new Control().getFoodItemByName("potato"));
        System.out.println("\n\n\n");
        //like search for food_items
        System.out.println("like search:");
        System.out.println(new Control().getFoodItemByNameLike("a"));



//        Constants.SEX sex = Constants.SEX.MALE;
//        Constants.ACTIVITY_LEVEL activity_level = Constants.ACTIVITY_LEVEL.SEDENTARY;
//        double weight = 84, height = 176;
//        int age = 25;
//
//        double LBM = Engine.estimateLBM(sex, weight, height);
//        double BMI = Engine.estimateBMI(sex, weight, height);
//        double Calories = Engine.estimateCalories(sex, weight, height, age, activity_level);
//        double Fat = Engine.estimateBodyFat(sex, weight, height, age);
//        double idealWeight = Engine.estimateIdealWeight(sex, height, age);
//
//        System.out.println("LBM "+LBM);
//        System.out.println("BMI "+BMI);
//        System.out.println("Calories "+Calories);
//        System.out.println("FAT "+Fat);
//        System.out.println("FAT+LBM "+((Fat*weight/100)+LBM));
//        System.out.println("Ideal weight: "+idealWeight);
    }
}
