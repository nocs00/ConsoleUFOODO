package com.ufood;

import com.ufood.DB.Constants;
import com.ufood.Model.Result;
import com.ufood.Model.Task;
import com.ufood.util.Engine;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by pdudenkov on 15.01.2016.
 */
public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        task.setUserID("masha90");
        task.setSex("female");
        ArrayList foodItems = new ArrayList();
        //Collections.addAll(foodItems, "apple", "potato", "chicken");
        Collections.addAll(foodItems, "rice", null);
        task.setFoodItems(foodItems);

        Result result = Engine.getResult(task);
        System.out.println(result.getDocument().toJson());

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
