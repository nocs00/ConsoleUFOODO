package com.ufood.db.dao.mongodb;

import com.ufood.model.Dish;
import com.ufood.model.Result;
import org.bson.types.ObjectId;

/**
 * Created by pdudenkov on 03.07.2016.
 */
public class ResultDao extends AbstractDao<Result> {
    @Override
    public Result getById(String objectId) {
        return getDatastore().get(Result.class, new ObjectId(objectId));
    }

    @Override
    public Result getByPropertyValue(String property, String value) {
        return getDatastore().find(Result.class, property, value).get();
    }

    @Override
    public void delete(Result result) {

    }
}
