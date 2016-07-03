package com.ufood.model;

import com.ufood.db.dao.mongodb.AbstractEntity;
import org.mongodb.morphia.annotations.Entity;

import java.util.List;

@Entity
public class Result extends AbstractEntity {
    private List<Menu> menus;
    private Task task;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
