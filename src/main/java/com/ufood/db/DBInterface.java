package com.ufood.db;

/**
 * Created by pdudenkov on 30.12.2015.
 */
public interface DBInterface {
    public Object select(String from, String what);
    public Object selectLike(String from, String what);
    public Object select(String from);
    public Object selectAll(String from);
    public Object selectAll(String from, Object content);
    public void update(String from, String what, Object content);
    public void insert(String where, Object content);
    public void delete(String where, Object content);
}
