package com.example.pulsa.networking;

import com.example.pulsa.model.ProductsResponse;
import com.example.pulsa.model.Pulsa;
import com.example.pulsa.model.PulsaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PulsaApi {
    @GET("product")
    Call<ProductsResponse> getProductList(@Query("page") String page,
                                           @Query("limit") String limit);
    @POST("pulsa")
    Call<PulsaResponse> postPulsa(@Body Pulsa body);
}
