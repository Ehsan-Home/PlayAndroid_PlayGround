package com.example.playground.MainActivity.RetrofitGet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    // relative URL for API call
    @GET("posts")
    Call<List<Post>> fetchPosts();

    @GET("posts/{id}/comments")
    // puts postId into id in path
    Call<List<Comment>> fetchComments(@Path("id") int postId);
}
