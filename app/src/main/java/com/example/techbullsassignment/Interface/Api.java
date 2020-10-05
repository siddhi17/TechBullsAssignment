package com.example.techbullsassignment.Interface;

import com.example.techbullsassignment.Models.MovieItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("?s=movie&apikey=131ecb13")
    Call<MovieItem> getMovies(@Query("page") int page, @Query("pagesize") int pagesize);

    @GET("&apikey=131ecb13")
    Call<MovieItem> searchMovies(@Query("s") String name, @Query("page") int page, @Query("pagesize") int pagesize);
}