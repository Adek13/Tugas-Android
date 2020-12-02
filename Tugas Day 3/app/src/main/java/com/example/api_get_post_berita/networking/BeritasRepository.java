package com.example.api_get_post_berita.networking;

import androidx.lifecycle.MutableLiveData;

import com.example.api_get_post_berita.model.Berita;
import com.example.api_get_post_berita.model.BeritaResponse;
import com.example.api_get_post_berita.model.BeritasResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritasRepository {
    private static BeritasRepository beritasRepository;

    public static BeritasRepository getInstance(){
        if (beritasRepository == null){
            beritasRepository = new BeritasRepository();
        }
        return beritasRepository;
    }

    private BeritaApi beritaApi;

    public BeritasRepository(){
        beritaApi = RetrofitService.cteateService(BeritaApi.class);
    }

    public MutableLiveData<BeritasResponse> getBeritas(String page, String limit){
        MutableLiveData<BeritasResponse> beritasData = new MutableLiveData<>();
        beritaApi.getNasabahsList(page, limit).enqueue(new Callback<BeritasResponse>() {
            @Override
            public void onResponse(Call<BeritasResponse> call,
                                   Response<BeritasResponse> response) {
                if (response.isSuccessful()){
                    beritasData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BeritasResponse> call, Throwable t) {
                beritasData.setValue(null);
            }
        });
        return beritasData;
    }


    public MutableLiveData<BeritaResponse> postBerita(Berita beritaPayload){
        MutableLiveData<BeritaResponse> beritaData = new MutableLiveData<>();
        beritaApi.postNasabah(beritaPayload).enqueue(new Callback<BeritaResponse>() {
            @Override
            public void onResponse(Call<BeritaResponse> call, Response<BeritaResponse> response) {
                if (response.isSuccessful()){
                    beritaData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BeritaResponse> call, Throwable t) {
                beritaData.postValue(null);
            }
        });
        return beritaData;
    }


}
