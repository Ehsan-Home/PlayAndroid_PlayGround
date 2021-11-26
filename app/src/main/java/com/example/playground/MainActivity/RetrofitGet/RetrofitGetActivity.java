package com.example.playground.MainActivity.RetrofitGet;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_get_activity);


//        fetchPosts();
        fetchComments();
    }

    private void fetchComments() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Comment>> call = jsonPlaceHolderApi.fetchComments(2);

        call.enqueue(fetchCommentsCallBack);
    }

    Callback<List<Comment>> fetchCommentsCallBack = new Callback<List<Comment>>() {
        @Override
        public void onResponse(@NonNull Call<List<Comment>> call, Response<List<Comment>> response) {
            if (!response.isSuccessful()) {
                Log.e("fetchComments", "server responded back with error " + response.code());
                return;
            }

            List<Comment> comments = response.body();

            for (Comment comment: comments) {
                Log.d("fetchComments", comment.toString());
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<Comment>> call, Throwable t) {
            Log.e("fetchComments", "error in communicating with server, code " +
                    t.getMessage());
        }
    };

    private void fetchPosts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.fetchPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Log.e("getPosts", "Server respond back error: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post post: posts) {
                    Log.d("getPosts", "post : " + post.toString());
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                Log.e("getPosts", "error in communication with server : " + t.getMessage());
            }
        });
    }
}
