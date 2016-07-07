package com.ufood.db.dao.mongodb;

import com.ufood.model.Dish;
import com.ufood.model.User;
import org.bson.types.ObjectId;

/**
 * Created by pdudenkov on 03.07.2016.
 */
public class UserDao extends AbstractDao<User> {
    @Override
    protected Class<User> getChildClass() {
        return User.class;
    }
}
