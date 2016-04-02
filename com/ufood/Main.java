package com.ufood;

//import com.ufood.api.Control;
//import static com.ufood.db.Constants.*;
//import static com.ufood.db.DBDriver.getDBDriver;
//
import static com.ufood.db.Constants.*;
//import com.ufood.model.Dish;
//import com.ufood.model.FoodItem;
//import com.ufood.model.Result;
//import com.ufood.model.Task;
//import com.ufood.schema.FoodItemSchema;
//import com.ufood.util.Engine;
//import org.bson.Document;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;


//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.client.config.ClientConfig;
//import com.sun.jersey.api.client.config.DefaultClientConfig;

import com.ufood.util.Engine;
import org.bson.Document;

import javax.ws.rs.core.MediaType;

/**
 * Created by pdudenkov on 15.01.2016.
 */
public class Main {
    public static void main(String[] args)  throws Throwable {
        String decoded = new String(org.apache.commons.codec.binary.Base64.decodeBase64("YWJjZGVmZw=="));
        System.out.println(decoded);

        Document doc = Document.parse(Engine.readJsonFile(RESOURCES_PATH + "fooditem_schema.json"));
        //sendLoginRequest();

//        Engine.fillURLsFoodItems();
//        Engine.fillURLsDishes();

//        System.out.println(DATABASE_NAME);
//        System.out.println(FOOD_COLLECTION);
//        System.out.println(DISH_COLLECTION);
//        System.out.println(MENU_COLLECTION);
//        System.out.println(TASK_COLLECTION);
//        System.out.println(RESULT_COLLECTION);
//        System.out.println(USER_ID);
//        System.out.println(IMAGE_PATH_DEBUG);
//        System.out.println(IMAGE_PATH);
//        System.out.println(IMAGE_BASE_URL);

//        Document foodItemDocument = getDBDriver().select(Constants.FOOD_COLLECTION, "apple2"); //for debug
//        try {
//            FoodItemSchema.checkAndApplySchema(foodItemDocument);
//        } catch (Exception e) {
//            System.out.println("exception thrown");
//        }

        /*
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
        task.setMetricUS(0);

        Result result = Engine.getResult(task);
        System.out.println(result.getDocument().toJson());
        */
//
//
//
//
//        //add food_item
//        System.out.println("Add fooditem:");
//        FoodItem test = new FoodItem();
//        test.setName("atest");
//        System.out.println(new Control().addFoodItem(test));
//        System.out.println("\n\n\n");
//        //add dish
//        System.out.println("Add dish:");
//        Dish dish = new Dish();
//        dish.setName("TESTIARA");
//        dish.setFoodItems(new HashMap<FoodItem, Double>());
//        System.out.println(new Control().addDish(dish));
//        System.out.println("\n\n\n");
//        //get fooditems
//        System.out.println("get all fooditems:");
//        System.out.println(new Control().getFoodItems());
//        System.out.println("\n\n\n");
//        //get dishes
//        System.out.println("Get all dishes:");
//        System.out.println(new Control().getDishes());
//        System.out.println("\n\n\n");
        //get dish by name:
//        System.out.println("get dish by name:");
//        System.out.println(new Control().searchDishes(new Document("name", "s")));
//        System.out.println("\n\n\n");
//        //absolute search
//        System.out.println("absolute search:");
//        System.out.println(new Control().searchFoodItems(new Document("name", "ap")));
//        System.out.println("\n\n\n");
//        //like search for food_items
//        System.out.println("like search:");
//        System.out.println(new Control().searchFoodItems(new Document("name", "po")));



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

//    public static void sendLoginRequest() {
//
//        class Customer {
//            String name;
//            int pin;
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public void setPin(int pin) {
//                this.pin = pin;
//            }
//        }
//
//        ClientConfig config = new DefaultClientConfig();
//        Client client = Client.create(config);
//        final String userName = "admin";
//        final String password = "admin";
//        String cred = userName + ":" + password;
//        WebResource service = client.resource(getBaseURI());
//
//
//        Customer customer = new Customer();
//        customer.setName("noob");
//        customer.setPin(123455);
//        ClientResponse response = service.path("user").path("login")
//                .accept(MediaType.APPLICATION_XML)
//                .header("Authorization", cred)
//                .post(ClientResponse.class, customer);
//
//        System.out.println(" response " + response.getEntity(String.class));
//    }
//
//    private static String  getBaseURI() {
//        return "https://ufoodo.com/mongorest/control/";
//    }
}
