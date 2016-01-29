package com.ufood.Model;

import com.ufood.DB.Documentable;
import org.bson.Document;
import java.util.ArrayList;
import static com.ufood.DB.Constants.*;

public class Menu implements Documentable {
    private String userID;
    private ArrayList<Dish> dishes;
    private double calories;//on 100 gramm
    private double protein;
    private double carbohydrate;
    private double fat;

    public Menu(String userID) {
        this.userID = userID;
        dishes = new ArrayList<Dish>();
        this.calories = 0;
        this.protein = 0;
        this.carbohydrate = 0;
        this.fat = 0;
    }

    public static Menu documentToMenu(Document menuDocument) {
        Menu menu = new Menu(menuDocument.getString(USER_ID));
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        for (String dishName : (String[])menuDocument.get(DISH_COLLECTION)) {
            Dish dish = new Dish(dishName);
            dishes.add(dish);
        }
        menu.dishes = dishes;
        menu.countNutrients(menu.dishes);
        return menu;
    }

    public void addDish(Dish dish) {
        this.dishes.add(dish);
        this.calories += dish.getCalories();
        this.protein += dish.getProtein();
        this.carbohydrate += dish.getCarbohydrate();
        this.fat += dish.getFat();
    }

    private void countNutrients(ArrayList<Dish> dishes) {
        for (Dish dish : dishes) {
            this.calories += dish.getCalories();
            this.protein += dish.getProtein();
            this.carbohydrate += dish.getCarbohydrate();
            this.fat += dish.getFat();
        }
    }

    public ArrayList<Dish> getDishes() {
        return dishes;
    }

    @Override
    public Document getDocument() {
//        ArrayList dishNames = new ArrayList();
//        for (Dish d : this.dishes) {
//            dishNames.add(d.getName());
//        }

        ArrayList dishDocuments = new ArrayList();
        for (Dish d : this.dishes) {
            dishDocuments.add(d.getDocument());
        }

        return new Document()
                .append("calories", this.calories)
                .append("proteins", this.protein)
                .append("carbohydrates", this.carbohydrate)
                .append("fats", this.fat)
                .append(DISH_COLLECTION, dishDocuments);
                //.append(DISH_COLLECTION, dishNames);

    }
}
