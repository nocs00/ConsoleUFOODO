package com.ufood.model;

import com.ufood.db.Constants;
import com.ufood.db.dao.mongodb.AbstractEntity;
import org.mongodb.morphia.annotations.Entity;

import static com.ufood.db.Constants.*;

/**
 * Created by pdudenkov on 30.12.2015.
 */

@Entity
public class User extends AbstractEntity {
    //todo link to user owner
    private SEX sex = SEX.MALE;

    private int age = 25;
    private double height = 175d;
    private double weight = 75d;

    private ACTIVITY_LEVEL activityLevel = ACTIVITY_LEVEL.LIGHT;
    private BODY_TYPE bodyType = BODY_TYPE.NORMAL;

    public SEX getSex() {
        return sex;
    }

    public void setSex(SEX sex) {
        this.sex = sex;
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

    public ACTIVITY_LEVEL getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ACTIVITY_LEVEL activityLevel) {
        this.activityLevel = activityLevel;
    }

    public BODY_TYPE getBodyType() {
        return bodyType;
    }

    public void setBodyType(BODY_TYPE bodyType) {
        this.bodyType = bodyType;
    }
}
