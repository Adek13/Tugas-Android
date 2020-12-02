package com.example.pulsa.networking;

import androidx.lifecycle.MutableLiveData;

import com.example.pulsa.model.ProductsResponse;
import com.example.pulsa.model.Pulsa;
import com.example.pulsa.model.PulsaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PulsaRepository {
    private static PulsaRepository pulsaRepository;

    public static PulsaRepository getInstance(){
        if (pulsaRepository == null){
            pulsaRepository = new PulsaRepository();
        }
        return pulsaRepository;
    }

    private PulsaApi pulsaApi;

    public PulsaRepository(){
        pulsaApi = RetrofitService.cteateService(PulsaApi.class);
    }

    public MutableLiveData<ProductsResponse> getProduct(String page, String limit){
        MutableLiveData<ProductsResponse> productData = new MutableLiveData<>();
        pulsaApi.getProductList(page, limit).enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call,
                                   Response<ProductsResponse> response) {
                if (response.isSuccessful()){
                    productData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                productData.setValue(null);
            }
        });
        return productData;
    }


    public MutableLiveData<PulsaResponse> postPulsa(Pulsa pulsaPayload){
        MutableLiveData<PulsaResponse> pulsaData = new MutableLiveData<>();
        pulsaApi.postPulsa(pulsaPayload).enqueue(new Callback<PulsaResponse>() {
           @Override
            public void onResponse(Call<PulsaResponse> call,
                                   Response<PulsaResponse> response) {
                if (response.isSuccessful()){
                    pulsaData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                pulsaData.setValue(null);
            }
        });
        return pulsaData;
    }

}
