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

//    public User(String userID) {
//        this.userID = userID;
//    }

    public ArrayList<Menu> getUserMenus() {
        ArrayList<Document> userMenus = getDBDriver().iterableToList(getDBDriver().selectAll(MENU_COLLECTION, new Document(USER_ID, this.userID)));

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
