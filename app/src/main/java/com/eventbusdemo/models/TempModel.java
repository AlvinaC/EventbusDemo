package com.eventbusdemo.models;

/**
 * Created by alvina.rasquinha on 28/03/18.
 */

public class TempModel {
    String name;
    Boolean isLike = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }
}
