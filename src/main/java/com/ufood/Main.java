package com.ufood;

import com.ufood.api.Control;
import static com.ufood.db.Constants.*;
import static com.ufood.db.DBDriver.getDBDriver;

import static com.ufood.db.Constants.*;
import com.ufood.model.Dish;
import com.ufood.model.FoodItem;
import com.ufood.model.Result;
import com.ufood.model.Task;
import com.ufood.util.Engine;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import com.ufood.model.FoodItem;
import com.ufood.util.Engine;
import org.bson.Document;

import javax.ws.rs.core.MediaType;

/**
 * Created by pdudenkov on 15.01.2016.
 */
public class Main {
    public static void main(String[] args)  throws Throwable {
        FoodItem foodItem = new FoodItem();
        Document document = getDBDriver().select(FOOD_COLLECTION);
    }

//    public static void sendLoginRequest() {
//
//        class Customer {
//            String name;
//            int pin;
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public void setPin(int pin) {
//                this.pin = pin;
//            }
//        }
//
//        ClientConfig config = new DefaultClientConfig();
//        Client client = Client.create(config);
//        final String userName = "admin";
//        final String password = "admin";
//        String cred = userName + ":" + password;
//        WebResource service = client.resource(getBaseURI());
//
//
//        Customer customer = new Customer();
//        customer.setName("noob");
//        customer.setPin(123455);
//        ClientResponse response = service.path("user").path("login")
//                .accept(MediaType.APPLICATION_XML)
//                .header("Authorization", cred)
//                .post(ClientResponse.class, customer);
//
//        System.out.println(" response " + response.getEntity(String.class));
//    }
//
//    private static String  getBaseURI() {
//        return "https://ufoodo.com/mongorest/control/";
//    }
}
