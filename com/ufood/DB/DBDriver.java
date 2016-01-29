package com.ufood.DB;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import static com.ufood.DB.Constants.*;
/**
 * Created by pdudenkov on 30.12.2015.
 */
public class DBDriver implements DBInterface { //singleton
    private final static MongoDatabase DB = new MongoClient().getDatabase(DATABASE_NAME);
    private static DBDriver instance;

    private DBDriver() {

    }

    public static DBDriver getDBDriver() {
        if (instance == null) {
            instance = new DBDriver();
        }
        return instance;
    }

    @Override
    public void insert(String where, Object content) {
        DB.getCollection(where).insertOne((Document)content);
    }

    @Override
    public FindIterable<Document> selectAll(String from) {
        return DB.getCollection(from).find();
    }

    @Override
    public FindIterable<Document> selectAll(String from, Object content) {
        return DB.getCollection(from).find((Document)content);
    }

    @Override
    public Document select(String from, String what) {
        return DB.getCollection(from).find(new Document("name", what)).first();
    }

    @Override
    public Document select(String from) {
        return DB.getCollection(from).find().first();
    }

    @Override
    public void update(String from, String what, Object content) {
        DB.getCollection(from).replaceOne(new Document("name", what), (Document)content) ; //possible would need to improve this decision;
    }

    @Override
    public void delete(String where, Object content) {
        DB.getCollection(where).deleteOne((Document)content);
    }

    public ArrayList<Document> iterableToList(FindIterable<Document> iterable) {
        ArrayList<Document> list = new ArrayList<Document>();

        for (Document document : iterable) {
            list.add(document);
        }

        return list;
    }
}
