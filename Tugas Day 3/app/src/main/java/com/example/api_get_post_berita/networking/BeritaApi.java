package com.example.api_get_post_berita.networking;

import com.example.api_get_post_berita.model.Berita;
import com.example.api_get_post_berita.model.BeritaResponse;
import com.example.api_get_post_berita.model.BeritasResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BeritaApi {
    @GET("berita")
    Call<BeritasResponse> getNasabahsList(@Query("page") String page,
                                          @Query("limit") String limit);
    @POST("berita")
    Call<BeritaResponse> postNasabah(@Body Berita body);
}
