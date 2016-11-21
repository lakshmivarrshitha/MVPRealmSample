package com.app.realmmvpsample.model;

import io.realm.RealmObject;

/**
 * Created by Venkatesan on 11/19/2016.
 */

public class Post extends RealmObject {
    private int userId;
    private int id;
    private String title;
    private String body;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
