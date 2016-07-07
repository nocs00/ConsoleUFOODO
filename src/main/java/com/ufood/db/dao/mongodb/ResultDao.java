package com.ufood.db.dao.mongodb;

import com.ufood.model.Dish;
import com.ufood.model.Result;
import org.bson.types.ObjectId;

/**
 * Created by pdudenkov on 03.07.2016.
 */
public class ResultDao extends AbstractDao<Result> {
    @Override
    protected Class<Result> getChildClass() {
        return Result.class;
    }
}
