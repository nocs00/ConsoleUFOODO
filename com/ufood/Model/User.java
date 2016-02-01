package com.ufood.Model;

import com.ufood.DB.Documentable;
import org.bson.Document;
import java.util.ArrayList;
import static com.ufood.DB.DBDriver.*;
import static com.ufood.DB.Constants.*;

/**
 * Created by pdudenkov on 30.12.2015.
 */
public class User implements Documentable {
    private String userID;
    private SEX sex = SEX.MALE;
    private int age = 25;
    private double height = 175d;
    private double weight = 75d;
    private ACTIVITY_LEVEL activity_level = ACTIVITY_LEVEL.LIGHT;
    private BODY_TYPE body_type = BODY_TYPE.NORMAL;

//    public User(String userID) {
//        this.userID = userID;
//    }

    public ArrayList<Menu> getUserMenus() {
        ArrayList<Document> userMenus = getDBDriver().selectAll(MENU_COLLECTION, new Document(USER_ID, this.userID));

        ArrayList<Menu> menus = new ArrayList<Menu>();
        for (Document menuDocument: userMenus) {
            menus.add(Menu.documentToMenu(menuDocument));
        }
        return menus;
    }

    @Override
    public Document getDocument() {
        return new Document(USER_ID, this.userID);
    }
}
