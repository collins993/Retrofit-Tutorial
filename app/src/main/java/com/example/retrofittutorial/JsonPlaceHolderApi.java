package com.example.retrofittutorial;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<ArrayList<Post>> getPost();
}
