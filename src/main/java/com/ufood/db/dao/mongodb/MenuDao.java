package com.ufood.db.dao.mongodb;

import com.ufood.model.Dish;
import com.ufood.model.Menu;
import org.bson.types.ObjectId;

/**
 * Created by pdudenkov on 03.07.2016.
 */
public class MenuDao extends AbstractDao<Menu> {
    @Override
    public Menu getById(String objectId) {
        return getDatastore().get(Menu.class, new ObjectId(objectId));
    }

    @Override
    public Menu getByPropertyValue(String property, String value) {
        return getDatastore().find(Menu.class, property, value).get();

    }

    @Override
    public void delete(Menu menu) {

    }
}
