package com.example.api_get_post_berita.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.api_get_post_berita.model.Berita;
import com.example.api_get_post_berita.model.BeritaResponse;
import com.example.api_get_post_berita.model.BeritasResponse;
import com.example.api_get_post_berita.networking.BeritasRepository;

public class BeritaViewModel extends ViewModel {
    private MutableLiveData<BeritasResponse> mutableLiveData;
    private BeritasRepository beritasRepository;
    private MutableLiveData<BeritaResponse> mutableBeritaLiveData;
//    private NasabahRepository nasabahRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        beritasRepository = BeritasRepository.getInstance();
        mutableLiveData = beritasRepository.getBeritas("1", "10");
    }

    public LiveData<BeritasResponse> getBeritasRepository() {
        return mutableLiveData;
    }
    public void refresh(String page, String limit ){
        if (mutableLiveData != null){
            mutableLiveData = beritasRepository.getBeritas(page, limit);
            return;
        }
        beritasRepository = BeritasRepository.getInstance();
        mutableLiveData = beritasRepository.getBeritas("1", "10");
    }

    public LiveData<BeritaResponse> postBeritaRepository(Berita beritaPayload) {
        if (mutableBeritaLiveData == null) {
//            System.out.println("tes11111");
            beritasRepository = BeritasRepository.getInstance();
        }
        mutableBeritaLiveData = beritasRepository.postBerita(beritaPayload);
//        System.out.println("tes222222");
        return mutableBeritaLiveData;
    }
}
