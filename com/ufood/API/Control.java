package com.ufood.API;

/**
 * Created by pdudenkov on 06.01.2016.
 */
import com.ufood.DB.Constants;
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
    @Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
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
        return "some user info";
    }

    @Path(USER+REGISTER)
    @GET
    @Produces()
    public String register() {
        return "some register logic";
    }

    @Path(USER+LOGIN)
    @GET
    public String login() {
        return "some login logic";
    }

    @Path(USER+HISTORY)
    @GET
    public String history() {
        return "history for logged on user";
    }

    @Path(ADMIN+ADD_FOODITEM)
    @PUT
    @Produces("text/plain")
    public String addFoodItem(
            FoodItem pojo
    ) {
        DBDriver.getDBDriver().insert(FOOD_COLLECTION, pojo.getDocument());

        return "food item inserted";
    }

    @Path(ADMIN+ADD_DISH)
    @PUT
    @Produces("text/plain")
    public String addDish(
            Dish pojo
    ) {
        DBDriver.getDBDriver().insert(DISH_COLLECTION, pojo.getDocument());

        return "dish inserted";
    }

    @Path(ADMIN+GET_FOODITEMS)
    @GET
    @Produces("text/plain")
    public String getFoodItems() {
        List<Document> docs = DBDriver.getDBDriver().selectAll(FOOD_COLLECTION);
        Document rootDoc = new Document(FOOD_COLLECTION, docs);

        return rootDoc.toJson();
    }

    @Path(ADMIN+GET_FOODITEM_BY_NAME)
    @PUT
    @Produces("text/plain")
    @Consumes("text/plain")
    public String getFoodItemByName(
            String name
    ) {
        return FoodItem.getFoodItemByName(name).getDocument().toJson();
    }

    @Path(ADMIN+GET_DISHES)
    @GET
    @Produces("text/plain")
    public String getDishes() {
        List<Document> docs = DBDriver.getDBDriver().selectAll(DISH_COLLECTION);
        Document rootDoc = new Document(DISH_COLLECTION, docs);

        return rootDoc.toJson();
    }

    @Path(ADMIN+GET_DISH_BY_NAME)
    @PUT
    @Produces("text/plain")
    @Consumes("text/plain")
    public String getDishByName(
            String name
    ) {
        return Dish.getFoodItemByName(name).getDocument().toJson();
    }
}
