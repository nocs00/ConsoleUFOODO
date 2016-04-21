package com.ufood.util;

import com.ufood.db.Constants;
import com.ufood.model.*;
import org.bson.Document;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import static com.ufood.db.Constants.*;
import static com.ufood.db.DBDriver.getDBDriver;
import org.apache.commons.codec.binary.*;

/**
 * Created by pdudenkov on 15.01.2016.
 */
public abstract class Engine {
    public static String readJsonFile(String path) throws IOException {
        StringBuffer sb = new StringBuffer();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = null;
        while ((line=bufferedReader.readLine())!=null) {
            sb.append(line);
        }

        fileReader.close();
        bufferedReader.close();
        return sb.toString();
    }

    public static String isLoggedIn(String encodedHeaderBase64) {
        String decodedHeader = new String(Base64.decodeBase64(encodedHeaderBase64.getBytes()));
        return decodedHeader;
    }

    public static void fillURLsFoodItems() {
        ArrayList<Document> food_items = getDBDriver().selectAll(FOOD_COLLECTION);

        File folder = new File(IMAGE_PATH);
        File[] list = folder.listFiles();

        for (Document food_item : food_items) {
            FoodItem foodItem = new FoodItem(food_item);
//            for (Object imageObject: foodItem.getImagesURL()) {
//                Document imageDocument = (Document)imageObject;
//                String imageURL = imageDocument.get("url").toString();
//                int width = new Integer(imageDocument.get("width").toString());
//                int height = new Integer(imageDocument.get("height").toString());
//
//            }
            ArrayList urls = new ArrayList();

            for (File file : list) {
                String fileNameLowCase = file.getName().toLowerCase();
                String foodItemNameLowCase = foodItem.getName().toLowerCase();

                if (!file.isDirectory() && fileNameLowCase.contains(foodItemNameLowCase)) {
                    int width = -1;
                    int height = -1;
                    try {
                        BufferedImage bimg = ImageIO.read(file);
                        width = bimg.getWidth();
                        height = bimg.getHeight();
                    } catch (IOException e) {

                    }

                    urls.add(new Document()
                            .append("url", IMAGE_BASE_URL + file.getName())
                            .append("width", width)
                            .append("height", height));
                }
            }
            foodItem.setImagesURL(urls);
            getDBDriver().update(FOOD_COLLECTION, foodItem.getName(), foodItem.getDocument());
        }
    }

    public static void fillURLsDishes() {
        ArrayList<Document> dishes = getDBDriver().selectAll(DISH_COLLECTION);

        File folder = new File(IMAGE_PATH);
        File[] list = folder.listFiles();

        for (Document dishDocument : dishes) {
            Dish dish = new Dish(dishDocument);
            ArrayList urls = new ArrayList();

            for (File file : list) {
                String fileNameLowCase = file.getName().toLowerCase();
                String dishNameLowCase = dish.getName().toLowerCase();

                if (!file.isDirectory() && fileNameLowCase.contains(dishNameLowCase)) {
                    int width = -1;
                    int height = -1;
                    try {
                        BufferedImage bimg = ImageIO.read(file);
                        width = bimg.getWidth();
                        height = bimg.getHeight();
                    } catch (IOException e) {

                    }

                    urls.add(new Document()
                            .append("url", IMAGE_BASE_URL + file.getName())
                            .append("width", width)
                            .append("height", height));
                }
            }
            dish.setImagesURL(urls);
            getDBDriver().update(FOOD_COLLECTION, dish.getName(), dish.getDocument());
        }
    }

    public static Result getResult(Task task) {
        Result result = new Result();
        result.setTask(task);
        //TODO : for multiple menus
        ArrayList<Menu> menus = new ArrayList<Menu>();
        Menu menu = new Menu(task.getUserID());
        ArrayList<String> foodItems = task.getFoodItems();
        ArrayList<Dish> dishes = Dish.findDishesByFoodItems(foodItems);
        for (Dish dish: dishes) {
            menu.addDish(dish);
        }
        menus.add(menu);
        result.setMenus(menus);
        return result;
    }

    public static double estimateLBM(Constants.SEX sex, double weightKg, double heightCm) { //Lean Body Mass = TotalWeight - Fat
        double resultLBM = 0d;

        double weightCoef = sex==Constants.SEX.MALE? 0.32810d:0.29569d;
        double heightCoef = sex==Constants.SEX.MALE? 0.33929d:0.41813d;
        double delta = sex==Constants.SEX.MALE? 29.5336d:43.2933d;

        resultLBM = (weightCoef * weightKg) + (heightCoef * heightCm) - delta;

        //if (Task.fat != null)  resultLBM = weightKg - Task.fat
        //if (Task.bodyType != null) switch(Task.bodyType) {...

        return resultLBM;
    }

    public static double estimateBMI(Constants.SEX sex, double weightKg, double heightCm) {//Body Mass Index
        double resultBMI = 0d;

        resultBMI = (weightKg*10000) / Math.pow(heightCm, 2);

        //what means
        if (resultBMI < 16)
            System.out.println("Severe Thinness");
        else if (resultBMI >= 16 && resultBMI < 17)
            System.out.println("Moderate Thinness");
        else if (resultBMI >= 17 && resultBMI < 18.5)
            System.out.println("Mild Thinness");
        else if (resultBMI >= 18.5 && resultBMI < 25)
            System.out.println("Normal");
        else if (resultBMI >= 25 && resultBMI < 30)
            System.out.println("Overweight");
        else if (resultBMI >= 30 && resultBMI < 35)
            System.out.println("Obese Class I");
        else if (resultBMI >= 35 && resultBMI < 40)
            System.out.println("Obese Class II");
        else if (resultBMI >= 40)
            System.out.println("Obese Class III");

        return resultBMI;
    }

    public static double estimateBMR(Constants.SEX sex, double weightKg, double heightCm, int ageY) {//Basal Metabolic Rate
        double resultBMR = 0d;

        //test
        //weightKg = estimateLBM(sex, weightKg, heightCm);

        if (sex == Constants.SEX.FEMALE) {
            resultBMR = 10 * weightKg + 6.25 * heightCm - 5 * ageY - 161;
        } else {
            resultBMR = 10 * weightKg + 6.25 * heightCm - 5 * ageY + 5;
        }

        return resultBMR;
    }

    public static double estimateCalories(Constants.SEX sex, double weightKg, double heightCm, int ageY, Constants.ACTIVITY_LEVEL activityLevel) {
        double resultCalories = 0d;
        double BMR = estimateBMR(sex, weightKg, heightCm, ageY);//Basal Metabolic Rate
        double activityLvl = 1.2d;

        switch (activityLevel) {
            case SEDENTARY:
                activityLvl = 1.2;
                break;
            case LIGHT:
                activityLvl = 1.4;
                break;
            case MODERATE:
                activityLvl = 1.55;
                break;
            case HIGH:
                activityLvl = 1.7;
                break;
            case EXTRA_HIGH:
                activityLvl = 1.9;
                break;
        }

        resultCalories = BMR * activityLvl;

        return resultCalories;
    }

    public static double estimateBodyFat(Constants.SEX sex, double weightKg, double heightCm, int ageY) {
        /**************reference
         * Description	                        Women	Men
         * Recommended amount	                20-25%	8-14%
         * Adults in United States, average	    22-25%	15-19%
         * Obese	                            30+%	25+%
         *
         * According to Health Check Systems, The American Council on Exercise has categorized the range of body fat percentages as follows:
         * Description	                        Women	Men
         * Essential fat	                    12-15%	2-5%
         * Athletes	                            16-20%	6-13%
         * Fitness	                            21-24%	14-17%
         * Acceptable	                        25-31%	18-25%
         * Obese	                            32%+	25%+
         * * from wikipedia.org   */

        double resultFat = 0d;

        resultFat = (1.2d * estimateBMI(sex, weightKg, heightCm)) + (0.23d * (double)ageY) - 5.4d;
        if (sex == Constants.SEX.MALE) {
            resultFat -= 1.8d; //-10.8 in original
        }

        return resultFat;
    }

    public static double estimateIdealWeight(Constants.SEX sex, double heightCm, int ageY) {
        double resultIdealWeight = 0;
        double multiplier = 0;
        double delta = 0;

        //J.D. Robinson Formula
        if (heightCm - 5d*30.48d > 0)  //5 feet * 30.48 cm
            delta = (heightCm - 5d*30.48d) / 2.54d;

        if (sex == Constants.SEX.FEMALE) {
            resultIdealWeight += 49d;
            multiplier = 1.7d;
        }
        else { //male
            resultIdealWeight += 52d;
            multiplier = 1.9d;
        }

        resultIdealWeight += delta*multiplier;

        return resultIdealWeight;
    }

    public static void insertResult(Result result) {
        getDBDriver().insert(RESULT_COLLECTION, result.getDocument());
    }
}
