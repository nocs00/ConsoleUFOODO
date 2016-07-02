package com.ufood.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.ufood.db.Constants.*;
/**
 * Created by pdudenkov on 30.12.2015.
 */
public class DBDriver implements DBInterface { //singleton
    private final static MongoCredential credential = MongoCredential.createCredential("root", "ufoodo", "root".toCharArray());
    private final static MongoClient client = new MongoClient(new ServerAddress("ds013172.mlab.com", 13172), Arrays.asList(credential));
    private final static MongoDatabase DB = client.getDatabase("ufoodo");
    //private final static MongoDatabase DB = new MongoClient().getDatabase(DATABASE_NAME);
    private static DBDriver instance;
    private Logger logger = Logger.getLogger(DBDriver.class.getName());

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
    public ArrayList<Document> selectAll(String from) {
        ArrayList<Document> list = iterableToList(DB.getCollection(from).find());
        return list;
    }

    @Override
    public ArrayList<Document> selectAll(String from, Object content) {
        ArrayList<Document> list = iterableToList(DB.getCollection(from).find((Document)content));;
        return list;
    }

    @Override
    public Document select(String from, String what) {
        Document document = DB.getCollection(from).find(new Document("name", what)).first();
        return document;
    }

    @Override
    public ArrayList<Document> selectLike(String from, String what) {
        ArrayList<Document> list = iterableToList(
                DB.getCollection(from)
                        .find(
                                new Document("name",
                                        new Document("$regex", "^" + what + "")))
                //.projection(
                //new Document("name", 1).append("_id", 0))
        );

        return list;
    }

    @Override
    public Document select(String from) {
        Document document = DB.getCollection(from).find().first();
        return document;
    }

    @Override
    public void update(String from, String what, Object content) {
        Document document = (Document)content;
        DB.getCollection(from).replaceOne(new Document("name", what), document) ; //possible would need to improve this decision;
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
