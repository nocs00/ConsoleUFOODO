package com.ufood.db.dao.mongodb;

import com.ufood.model.Dish;
import org.bson.types.ObjectId;

/**
 * Created by pdudenkov on 03.07.2016.
 */
public class DishDao extends AbstractDao<Dish> {
    @Override
    public Dish getById(String objectId) {
        return getDatastore().get(Dish.class, new ObjectId(objectId));
    }

    @Override
    public Dish getByPropertyValue(String property, String value) {
        return getDatastore().find(Dish.class, property, value).get();
    }

    @Override
    public void delete(Dish dish) {

    }
}
