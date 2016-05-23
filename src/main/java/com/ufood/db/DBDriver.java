package com.ufood.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.ufood.schema.SchemaStorage;
import com.ufood.schema.SchemaValidator;
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
//        To connect using the mongo shell:
//        mongo ds013172.mlab.com:13172/ufoodo -u <dbuser> -p <dbpassword>
//                To connect using a driver via the standard MongoDB URI (what's this?):
//        mongodb://<dbuser>:<dbpassword>@ds013172.mlab.com:13172/ufoodo
//    private final static MongoCredential credential = MongoCredential.createCredential("root", "ufoodo", "root".toCharArray());
//    private final static MongoClient client = new MongoClient(new ServerAddress("ds013172.mlab.com", 13172), Arrays.asList(credential));
//    private final static MongoDatabase DB = client.getDatabase("ufoodo");
    private final static MongoDatabase DB;
    private static DBDriver instance;
    private Logger logger = Logger.getLogger(DBDriver.class.getName());
    private final static boolean DEBUG = true;

    static {
        if (DEBUG) {
            MongoClient client = new MongoClient("146.185.165.237", 27017);
            DB = client.getDatabase("test");
        } else {
            DB = new MongoClient().getDatabase(DATABASE_NAME);;
        }
    }

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

    private Document checkSchema(String schemaName, Document document) {
        Document copy = new Document(document);
        try {
            if (schemaName.equals(FOOD_COLLECTION)) document = SchemaValidator.checkAndApplySchema(schemaName, document);
            return document;
        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("Try to apply valid schema for: %s", schemaName));
            return copy;
        }
    }

    @Override
    public ArrayList<Document> selectAll(String from) {
        ArrayList<Document> list = iterableToList(DB.getCollection(from).find());
        for (Document document : list)
            document = checkSchema(from, document);

        return list;
    }

    @Override
    public ArrayList<Document> selectAll(String from, Object content) {
        ArrayList<Document> list = iterableToList(DB.getCollection(from).find((Document)content));;
//        for (Document document : list)
//            document = checkSchema(from, document);

        return list;
    }

    @Override
    public Document select(String from, String what) {
        Document document = DB.getCollection(from).find(new Document("name", what)).first();
//        document = checkSchema(from, document);
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
//        for (Document document : list)
//            document = checkSchema(from, document);

        return list;
    }

    @Override
    public Document select(String from) {
        Document document = DB.getCollection(from).find().first();
        //document = checkSchema(from, document);
        return document;
    }

    @Override
    public void update(String from, String what, Object content) {
        Document document = (Document)content;
        //document = checkSchema(from, document);
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
