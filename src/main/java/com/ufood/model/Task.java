package com.ufood.model;


import com.ufood.db.dao.mongodb.AbstractEntity;
import org.mongodb.morphia.annotations.Entity;

import java.util.List;

import static com.ufood.db.Constants.*;
import static com.ufood.db.Constants.ACTIVITY_LEVEL.*;

@Entity
public class Task extends AbstractEntity {
    private User user;
    private List<String> foodItemNames;
    private boolean metricUS = false;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
