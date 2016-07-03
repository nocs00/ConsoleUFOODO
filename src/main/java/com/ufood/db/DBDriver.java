//package com.ufood.db;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import static com.ufood.db.Constants.*;
///**
// * Created by pdudenkov on 30.12.2015.
// */
//public class DBDriver { //singleton
////        To connect using the mongo shell:
////        mongo ds013172.mlab.com:13172/ufoodo -u <dbuser> -p <dbpassword>
////                To connect using a driver via the standard MongoDB URI (what's this?):
////        mongodb://<dbuser>:<dbpassword>@ds013172.mlab.com:13172/ufoodo
////    private final static MongoCredential credential = MongoCredential.createCredential("root", "ufoodo", "root".toCharArray());
////    private final static MongoClient client = new MongoClient(new ServerAddress("ds013172.mlab.com", 13172), Arrays.asList(credential));
////    private final static MongoDatabase DB = client.getDatabase("ufoodo");
//    private final static MongoDatabase DB;
//    private static DBDriver instance;
//    private Logger logger = Logger.getLogger(DBDriver.class.getName());
//    private final static boolean DEBUG = true;
//
//    static {
//        if (DEBUG) {
//            MongoClient client = new MongoClient("146.185.165.237", 27017);
//            DB = client.getDatabase("test");
//        } else {
//            DB = new MongoClient().getDatabase(DB_NAME);;
//        }
//    }
//
//    private DBDriver() {
//
//    }
//
//    public static DBDriver getDBDriver() {
//        if (instance == null) {
//            instance = new DBDriver();
//        }
//        return instance;
//    }
//}
