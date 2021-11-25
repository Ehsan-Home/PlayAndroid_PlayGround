package com.example.playground.MainActivity.RetrofitGet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    // relative URL for API call
    @GET("posts")
    Call<List<Post>> fetchPosts();
}
