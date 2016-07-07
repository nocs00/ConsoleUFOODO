package com.ufood.db.dao.mongodb;

import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import static com.ufood.db.Constants.*;

/**
 * Created by pdudenkov on 03.07.2016.
 */
public abstract class AbstractDao<T> implements AutoCloseable {
    private Datastore datastore;
    private MongoClient mongoClient;

    protected abstract Class<T> getChildClass();

    public Datastore getDatastore() {
        if (datastore == null) {
            mongoClient = new MongoClient();
            Morphia morphia = new Morphia();
            datastore = morphia.createDatastore(mongoClient, DB_NAME);

            morphia.mapPackage("com.ufood.db.dao.mongodb");
            morphia.mapPackage("com.ufood.model");
        }
        return datastore;
    }

    public T save(T t) {
        getDatastore().save(t);
        return t;
    }

    public T getById(String objectId) {
        return getDatastore().get(getChildClass(), new ObjectId(objectId));

    }

    public T getByPropertyValue(String property, String value) {
        return getDatastore().find(getChildClass(), property, value).get();

    }

    public void delete(T t) {

    }

    @Override
    public void close() throws Exception {
        mongoClient.close();
    }
}
