package com.example.playground.MainActivity.RetrofitGet;

import com.google.gson.annotations.SerializedName;

public class Comment {

    private int postId;

    private int id;

    private String name;

    private String email;

    @SerializedName("body")
    private String text;
}
