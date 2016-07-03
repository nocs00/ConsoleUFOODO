package com.ufood.db.dao.mongodb;

import com.ufood.model.Dish;
import com.ufood.model.FoodItem;
import org.bson.types.ObjectId;

/**
 * Created by pdudenkov on 03.07.2016.
 */
public class FoodItemDao extends AbstractDao<FoodItem> {
    @Override
    public FoodItem getById(String objectId) {
        return getDatastore().get(FoodItem.class, new ObjectId(objectId));
    }

    @Override
    public FoodItem getByPropertyValue(String property, String value) {
        return getDatastore().find(FoodItem.class, property, value).get();

    }

    @Override
    public void delete(FoodItem foodItem) {

    }
}
