package com.example.playground.MainActivity.RetrofitGet;

import com.google.gson.annotations.SerializedName;

public class Post {

    private String title;

    @SerializedName("body")
    private String text;

    private int id;

    private int userId;

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", id=" + id +
                ", userId=" + userId +
                '}';
    }
}
