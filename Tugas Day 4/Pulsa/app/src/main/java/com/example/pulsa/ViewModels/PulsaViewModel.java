package com.example.pulsa.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pulsa.model.ProductsResponse;
import com.example.pulsa.model.Pulsa;
import com.example.pulsa.model.PulsaResponse;
import com.example.pulsa.networking.PulsaRepository;

public class PulsaViewModel extends ViewModel {
    private static MutableLiveData<ProductsResponse> mutableLiveData;
    private static PulsaRepository productsRepository;
    private static MutableLiveData<PulsaResponse> mutablePulsaLiveData;
//    private NasabahRepository nasabahRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        productsRepository = PulsaRepository.getInstance();
        mutableLiveData = productsRepository.getProduct("1", "10");
    }
    public static void refresh(String page, String limit){
        if (mutableLiveData != null){
            mutableLiveData = productsRepository.getProduct(page, limit);
            return;
        }
        productsRepository = PulsaRepository.getInstance();
        mutableLiveData = productsRepository.getProduct("1", "10");
    }

    public static LiveData<ProductsResponse> getPulsaRepository() {
        return mutableLiveData;
    }
    public static LiveData<PulsaResponse> postPulsa(Pulsa pulsaPayload) {
        if (mutablePulsaLiveData == null) {
//            System.out.println("tes11111");
            productsRepository = PulsaRepository.getInstance();
        }
        mutablePulsaLiveData = productsRepository.postPulsa(pulsaPayload);
//        System.out.println("tes222222");
        return mutablePulsaLiveData;
    }
}
