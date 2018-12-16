package com.min.boostcamp.boostcampassignment.util;

import com.min.boostcamp.boostcampassignment.vo.Repo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NaverApiInterface {

    @GET("v1/search/movie.json")
    Call<Repo> getMovieList(
            @Header("X-Naver-Client-Id") String id,
            @Header("X-Naver-Client-Secret") String secret,
            @Query("Query") String query,
            @Query("start") int start
    );
}