package com.ufood.API;

/**
 * Created by pdudenkov on 06.01.2016.
 */
import com.ufood.DB.Constants;
import com.ufood.DB.DBDriver;
import com.ufood.Model.FoodItem;
import com.ufood.util.Engine;
import com.ufood.Model.Result;
import com.ufood.Model.Task;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
        DBDriver.getDBDriver().insert(Constants.FOOD_COLLECTION, pojo.getDocument());
        
        return "food item inserted";
    }

    @Path(ADMIN+ADD_DISH)
    @GET
    @Produces("text/plain")
    public String addDish(
            //Dish POJO
    ) {
        return "dish inserted";
    }

    @Path(ADMIN+GET_FOODITEMS)
    @GET
    @Produces("text/plain")
    public String getFoodItems() {
        return "list of all food items";
    }

    @Path(ADMIN+GET_DISHES)
    @GET
    @Produces("text/plain")
    public String getDishes() {
        return "list of all dishes";
    }
}
