package com.ufood.model;


import com.ufood.db.dao.mongodb.AbstractEntity;
import org.mongodb.morphia.annotations.Entity;

import java.util.List;

import static com.ufood.db.Constants.*;
import static com.ufood.db.Constants.ACTIVITY_LEVEL.*;

@Entity
public class Task extends AbstractEntity {
    //todo link to user owner
    private SEX sex = SEX.MALE;
    private int age = 25;
    private double height = 175d;
    private double weight = 75d;
    private ACTIVITY_LEVEL activityLevel = LIGHT;
    private BODY_TYPE bodyType = BODY_TYPE.NORMAL;
    private List<String> foodItemNames;
    private boolean metricUS = false;

    public SEX getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<String> getFoodItemNames() {
        return foodItemNames;
    }

    public void setFoodItemNames(List<String> foodItemNames) {
        this.foodItemNames = foodItemNames;
    }

    public boolean isMetricUS() {
        return metricUS;
    }

    public void setMetricUS(boolean metricUS) {
        this.metricUS = metricUS;
    }

    public String getActivityLevel() {
        switch (this.activityLevel) {
            case SEDENTARY:
                return "sedentary";
            case LIGHT:
                return "light";
            case MODERATE:
                return "moderate";
            case HIGH:
                return "high";
            case EXTRA_HIGH:
                return "extra_high";
            default:
                return "light";
        }
    }

    public void setActivityLevel(String activity_level) {
        switch (activity_level) {
            case "sedentary" :
                this.activityLevel = SEDENTARY;
                break;
            case "light" :
                this.activityLevel = LIGHT;
                break;
            case "moderate" :
                this.activityLevel = MODERATE;
                break;
            case "high" :
                this.activityLevel = HIGH;
                break;
            case "extra_high" :
                this.activityLevel = EXTRA_HIGH;
                break;
            default:
                this.activityLevel = LIGHT;
                break;
        }
    }

    public String getBodyType() {
        switch (this.bodyType) {
            case FAT:
                return "fat";
            case NORMAL:
                return "normal";
            case ATHLETE:
                return "athlete";
            case THIN:
                return "thin";
            default:
                return "normal";
        }
    }

    public void setBodyType(String body_type) {
        switch (body_type) {
            case "fat" :
                this.bodyType = BODY_TYPE.FAT;
                break;
            case "normal" :
                this.bodyType = BODY_TYPE.NORMAL;
                break;
            case "athlete" :
                this.bodyType = BODY_TYPE.ATHLETE;
                break;
            case "thin" :
                this.bodyType = BODY_TYPE.THIN;
                break;
            default:
                this.bodyType = BODY_TYPE.NORMAL;
                break;
        }
    }

    public void setSex(String sex) {
        if (sex.equals("male") || sex.equals("m")) {
            this.sex = SEX.MALE;
        } else if (sex.equals("female") || sex.equals("f")) {
            this.sex = SEX.FEMALE;
        }
        if (this.sex == null)
            this.sex = SEX.MALE;
    }
}
