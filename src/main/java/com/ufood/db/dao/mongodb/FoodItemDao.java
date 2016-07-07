package com.ufood.db.dao.mongodb;

import com.ufood.model.Dish;
import com.ufood.model.FoodItem;
import org.bson.types.ObjectId;

/**
 * Created by pdudenkov on 03.07.2016.
 */
public class FoodItemDao extends AbstractDao<FoodItem> {
    @Override
    protected Class<FoodItem> getChildClass() {
        return FoodItem.class;
    }
}
