package com.example.week11_31720;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("posts.json")
    Call<List<ListModel>> getList();
}
