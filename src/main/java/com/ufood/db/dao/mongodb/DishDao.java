package com.ufood.db.dao.mongodb;

import com.ufood.model.Dish;
import org.bson.types.ObjectId;

/**
 * Created by pdudenkov on 03.07.2016.
 */
public class DishDao extends AbstractDao<Dish> {
    @Override
    protected Class<Dish> getChildClass() {
        return Dish.class;
    }
}
