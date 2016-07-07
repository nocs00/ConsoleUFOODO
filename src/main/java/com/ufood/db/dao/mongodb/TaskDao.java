package com.ufood.db.dao.mongodb;

import com.ufood.model.Dish;
import com.ufood.model.Task;
import org.bson.types.ObjectId;

/**
 * Created by pdudenkov on 03.07.2016.
 */
public class TaskDao extends AbstractDao<Task> {
    @Override
    protected Class<Task> getChildClass() {
        return Task.class;
    }
}
