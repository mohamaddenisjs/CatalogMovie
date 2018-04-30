package com.denis.pragmadev.catalogmovie.services;

import com.denis.pragmadev.catalogmovie.model.TmdbResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbApi {

    @GET("search/movie?api_key=d3d24b7cfd41f457977066799293c841&language=en-US")
    Call<TmdbResponse> searchMovie(@Query("query") String name);

}
