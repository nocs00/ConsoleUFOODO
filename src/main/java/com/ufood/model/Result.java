package com.ufood.model;

import com.ufood.db.Documentable;
import org.bson.Document;
import java.util.ArrayList;
import static com.ufood.db.Constants.*;

public class Result implements Documentable {
    private ArrayList<Menu> menus;
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public Document getDocument() {
        //TODO: for multiple menus
        Document resultDocument = new Document(USER_ID, this.task.getUserID());
        resultDocument.append("task", task.getDocument());
        for (int j = 0; j < this.menus.size(); j++) {
            Menu menu = this.menus.get(j);
            Document menuDocument = menu.getDocument();
            resultDocument.append("menu"+j, menuDocument);
        }
        return resultDocument;
    }
}
