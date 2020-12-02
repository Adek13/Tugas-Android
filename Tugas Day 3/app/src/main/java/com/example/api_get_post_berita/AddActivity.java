package com.example.api_get_post_berita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.api_get_post_berita.adapter.BeritaAdapter;
import com.example.api_get_post_berita.model.Berita;
import com.example.api_get_post_berita.viewmodels.BeritaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private Berita berita = new Berita();

    EditText titleEditText,categoryEditText,urlEditText;
    FloatingActionButton refreshButton;
    Button saveButton;
    ImageView addImageView;
    List<Berita> beritas;
    ArrayList<Berita> beritaArrayList = new ArrayList<>();
    BeritaAdapter beritaAdapter;

    BeritaViewModel beritaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findViewById();
        onClickGroup();
        init();

    }

    void init(){
        beritaAdapter = new BeritaAdapter(getApplicationContext(), beritaArrayList);

        beritaViewModel = ViewModelProviders.of(this).get(BeritaViewModel.class);

        beritaViewModel.init();
        beritaViewModel.getBeritasRepository().observe(this, beritasResponse -> {
            beritas = beritasResponse.getData();
            beritaArrayList.clear();
            beritaArrayList.addAll(beritas);
            beritaAdapter.notifyDataSetChanged();
        });
    }

    void findViewById(){
        titleEditText = findViewById(R.id.titleEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
        urlEditText = findViewById(R.id.urlEditText);

        saveButton = findViewById(R.id.saveButton);
        refreshButton = findViewById(R.id.refreshButton);

        addImageView = findViewById(R.id.addImageView);
    }

    void onClickGroup(){
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Berita beritaPayload = new Berita();
                beritaPayload.setTitle(titleEditText.getText().toString());
                beritaPayload.setCategory(categoryEditText.getText().toString());
                beritaPayload.setUrl(urlEditText.getText().toString());
                postBerita(beritaPayload);
            }
        });
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (urlEditText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Url Is Empty!", Toast.LENGTH_SHORT).show();
                }else{
                    Glide.with(AddActivity.this)
                            .load(urlEditText.getText().toString())
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(addImageView);
                }
            }
        });
    }

    void postBerita(Berita beritaPayload){
//        Toast.makeText(getApplicationContext(), beritaPayload.getCategory(), Toast.LENGTH_SHORT).show();
//        System.out.println(beritaPayload.getTitle());
//        System.out.println(beritaPayload.getCategory());
//        System.out.println(beritaPayload.getUrl());

        beritaViewModel.postBeritaRepository(beritaPayload).observe(this, beritaResponse -> {
            berita = beritaResponse.getData();
            finish();
        });

    }
}