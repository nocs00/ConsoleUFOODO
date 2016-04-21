package com.ufood.api;

/**
 * Created by pdudenkov on 06.01.2016.
 */
import com.ufood.db.DBDriver;
import com.ufood.model.Dish;
import com.ufood.model.FoodItem;
import com.ufood.util.Engine;
import com.ufood.model.Result;
import com.ufood.model.Task;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

import static com.ufood.db.Constants.*;
import static com.ufood.api.APIConstants.*;


@Path(CONTROL)
public class Control {
    private Logger logger = Logger.getLogger(Control.class.getName());

//    @Path("test-login")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON + SEPARATOR + CHARSET)
//    public Response login(
//            @Context HttpHeaders httpHeaders
//    ) {
//        List<String> authList = httpHeaders.getRequestHeaders().get("Authorization");
//        for (String authorization  : authList) {
//            if (authorization != null && authorization.startsWith("Basic")) {
//                // Authorization: Basic base64credentials
//                String base64Credentials = authorization.substring("Basic".length()).trim();
//                String credentials = new String(Base64.getDecoder().decode(base64Credentials),
//                        Charset.forName("UTF-8"));
//                // credentials = username:password
//                final String[] values = credentials.split(":", 2);
//            }
//        }
//
//        return Response.status(200).build();
//    }

    @Path("/check-deployment")
    @GET
    @Produces("text/plain")
    public String checkDeployment() {
        return "maven plus tomcat automatic deployment successful";
    }

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
    @POST
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_JSON + SEPARATOR + CHARSET)
    public String login(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationInfo) {
        final String decoded = Engine.isLoggedIn(authorizationInfo);
        Document root = new Document("message", String.format("Your authorization http header: %s ; original: %s", decoded, authorizationInfo));
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

    @Path(ADMIN+FILL_URLS)
    @GET
    public void fillURLs() {
        Engine.fillURLsFoodItems();
        Engine.fillURLsDishes();
    }
}
