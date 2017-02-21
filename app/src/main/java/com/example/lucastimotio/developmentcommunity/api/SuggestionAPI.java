package com.example.lucastimotio.developmentcommunity.api;

import com.example.lucastimotio.developmentcommunity.domain.Suggestion;
import com.google.gson.annotations.JsonAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by lucastimotio on 20/02/17.
 */

public interface SuggestionAPI {

    @GET("/api/v1/suggestions")
    Call<Suggestion> get();

    @POST("/api/v1/suggestions")
    Call<Suggestion> post(@Body Suggestion suggestion);

}
