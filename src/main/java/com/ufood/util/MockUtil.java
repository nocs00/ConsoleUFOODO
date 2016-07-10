package com.ufood.util;

import com.ufood.db.Constants;
import com.ufood.model.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pdudenkov on 10.07.2016.
 */
public class MockUtil {
    private static final Random RANDOM = new Random();

    public static ObjectId generateObjectId() {
        char[] hexAlphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 24; i++) {
            sb.append(hexAlphabet[RANDOM.nextInt(16)]);
        }

        return new ObjectId(sb.toString());
    }

    public static FoodItem generateFoodItem() {
        FoodItem foodItem = new FoodItem();
        foodItem.setId(generateObjectId());
        foodItem.setVersion((long)RANDOM.nextInt(11));
        foodItem.setCalories((double)RANDOM.nextInt(401));
        foodItem.setProtein((double)RANDOM.nextInt(16));
        foodItem.setCarbohydrate((double)RANDOM.nextInt(16));
        foodItem.setFat((double)RANDOM.nextInt(16));
        foodItem.setQuantity(1);
        foodItem.setName("foodItem"+RANDOM.nextInt());
        return foodItem;
    }

    public static List<FoodItem> generateFoodItemList() {
        int size = RANDOM.nextInt(5)+1;
        List<FoodItem> foodItems = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            foodItems.add(generateFoodItem());
        }
        return foodItems;
    }

    public static Dish generateDish() {
        Dish dish = new Dish();
        dish.setId(generateObjectId());
        dish.setVersion((long)RANDOM.nextInt(11));
        dish.setCalories((double)RANDOM.nextInt(401));
        dish.setProtein((double)RANDOM.nextInt(16));
        dish.setCarbohydrate((double)RANDOM.nextInt(16));
        dish.setFat((double)RANDOM.nextInt(16));
        dish.setFoodItems(generateFoodItemList());
        dish.setName("dish"+RANDOM.nextInt());
        return dish;
    }

    public static List<Dish> generateDishList() {
        int size = RANDOM.nextInt(5)+1;
        List<Dish> dishes = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            dishes.add(generateDish());
        }
        return dishes;
    }

    public static Menu generateMenu() {
        Menu menu = new Menu();
        menu.setId(generateObjectId());
        menu.setVersion((long)RANDOM.nextInt(11));
        menu.setCalories((double)RANDOM.nextInt(401));
        menu.setProtein((double)RANDOM.nextInt(16));
        menu.setCarbohydrate((double)RANDOM.nextInt(16));
        menu.setFat((double)RANDOM.nextInt(16));
        menu.setDishes(generateDishList());
        return menu;
    }

    public static List<Menu> generateMenuList() {
        int size = RANDOM.nextInt(3)+1;
        List<Menu> menus = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            menus.add(generateMenu());
        }
        return menus;
    }

    public static List<String> generateFoodItemNames() {
        List<FoodItem> foodItems = generateFoodItemList();
        List<String> foodItemNames = new ArrayList<>(foodItems.size());
        for (FoodItem foodItem : foodItems) {
            foodItemNames.add(foodItem.getName());
        }
        return foodItemNames;
    }

    public static User generateUser() {
        User user = new User();
        user.setId(generateObjectId());
        user.setVersion((long)RANDOM.nextInt(11));
        user.setHeight(140 + RANDOM.nextInt(71));
        user.setWeight(40 + RANDOM.nextInt(81));
        user.setActivityLevel(Constants.ACTIVITY_LEVEL.values()[RANDOM.nextInt(5)]);
        user.setAge(10 + RANDOM.nextInt(60));
        user.setBodyType(Constants.BODY_TYPE.values()[RANDOM.nextInt(4)]);
        user.setSex(Constants.SEX.values()[RANDOM.nextInt(2)]);
        return user;
    }

    public static Task generateTask() {
        Task task = new Task();
        task.setUser(generateUser());
        task.setId(generateObjectId());
        task.setVersion((long)RANDOM.nextInt(11));
        task.setMetricUS(RANDOM.nextBoolean());
        task.setFoodItemNames(generateFoodItemNames());
        return task;
    }

    public static Result generateResult() {
        Result result = new Result();
        result.setId(generateObjectId());
        result.setVersion((long)RANDOM.nextInt(11));
        result.setMenus(generateMenuList());
        result.setTask(generateTask());
        return result;
    }
}