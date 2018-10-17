package com.example.maulana.movie.UI.rest;

import com.example.maulana.movie.UI.model.ResponseMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/upcoming")
    Call<ResponseMovie> getUpComingMovie(@Query("api_key") String apikey,
                                         @Query("language") String language );

}