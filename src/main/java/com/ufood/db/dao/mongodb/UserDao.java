package com.ufood.db.dao.mongodb;

import com.ufood.model.Dish;
import com.ufood.model.User;
import org.bson.types.ObjectId;

/**
 * Created by pdudenkov on 03.07.2016.
 */
public class UserDao extends AbstractDao<User> {
    @Override
    public User getById(String objectId) {
        return getDatastore().get(User.class, new ObjectId(objectId));
    }

    @Override
    public User getByPropertyValue(String property, String value) {
        return getDatastore().find(User.class, property, value).get();
    }

    @Override
    public void delete(User user) {

    }
}
