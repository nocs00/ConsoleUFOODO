package com.ufood.db.dao.mongodb;

import com.ufood.model.Dish;
import com.ufood.model.Task;
import org.bson.types.ObjectId;

/**
 * Created by pdudenkov on 03.07.2016.
 */
public class TaskDao extends AbstractDao<Task> {
    @Override
    public Task getById(String objectId) {
        return getDatastore().get(Task.class, new ObjectId(objectId));
    }

    @Override
    public Task getByPropertyValue(String property, String value) {
        return getDatastore().find(Task.class, property, value).get();
    }

    @Override
    public void delete(Task task) {

    }
}
