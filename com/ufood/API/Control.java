package com.ufood.API;

/**
 * Created by pdudenkov on 06.01.2016.
 */
import com.ufood.DB.DBDriver;
import com.ufood.Model.Dish;
import com.ufood.Model.FoodItem;
import com.ufood.util.Engine;
import com.ufood.Model.Result;
import com.ufood.Model.Task;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import static com.ufood.DB.Constants.*;

import static com.ufood.API.APIConstants.*;


@Path(CONTROL)
public class Control {
    @Path(MENU)
    @PUT
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_JSON + SEPARATOR + CHARSET)
    public String getMenu(
            Task task
    ) {
        Result result = Engine.getResult(task);
        Engine.insertResult(result);


        return result.getDocument().toJson();
    }

    @Path(USER)
    @GET
    @Produces("text/plain")
    public String getUser() {
        Document root = new Document("message", "some user info");
        return root.toJson();
    }

    @Path(USER+REGISTER)
    @GET
    @Produces()
    public String register() {
        Document root = new Document("message", "some register logic");
        return root.toJson();
    }

    @Path(USER+LOGIN)
    @GET
    public String login() {
        Document root = new Document("message", "some login logic");
        return root.toJson();
    }

    @Path(USER+HISTORY)
    @GET
    public String history() {
        Document root = new Document("message", "history for logged on user");
        return root.toJson();
    }

    @Path(ADMIN+ADD_FOODITEM)
    @PUT
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_JSON + SEPARATOR + CHARSET)
    public String addFoodItem(
            FoodItem pojo
    ) {
        DBDriver.getDBDriver().insert(FOOD_COLLECTION, pojo.getDocument());

        Document root = new Document("message", "food_item inserted");
        return root.toJson();
    }

    @Path(ADMIN+ADD_DISH)
    @PUT
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_JSON + SEPARATOR + CHARSET)
    public String addDish(
            Dish pojo
    ) {
        DBDriver.getDBDriver().insert(DISH_COLLECTION, pojo.getDocument());

        Document root = new Document("message", "dish inserted");
        return root.toJson();
    }

    @Path(GET_FOODITEMS)
    @GET
    @Produces("text/plain")
    public String getFoodItems() {
        List<Document> docs = DBDriver.getDBDriver().selectAll(FOOD_COLLECTION);
        Document rootDoc = new Document(FOOD_COLLECTION, docs);

        return rootDoc.toJson();
    }

    @Path(SEARCH_FOODITEMS)
    @PUT
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_JSON + SEPARATOR + CHARSET)
    public String searchFoodItems(
            Document nameDoc
    ) {
        String name = nameDoc.getString("name");
        Document root = new Document("found_items", DBDriver.getDBDriver().selectLike(FOOD_COLLECTION, name));
        return root.toJson();
    }

    @Path(GET_DISHES)
    @GET
    @Produces("text/plain")
    public String getDishes() {
        List<Document> docs = DBDriver.getDBDriver().selectAll(DISH_COLLECTION);
        Document rootDoc = new Document(DISH_COLLECTION, docs);

        return rootDoc.toJson();
    }

    @Path(SEARCH_DISHES)
    @PUT
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_JSON + SEPARATOR + CHARSET)
    public String searchDishes(
            Document nameDoc
    ) {
        String name = nameDoc.getString("name");
        Document root = new Document("found_items", DBDriver.getDBDriver().selectLike(DISH_COLLECTION, name));
        return root.toJson();
    }
}
