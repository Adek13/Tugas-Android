package com.example.api_get_post_berita.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.api_get_post_berita.R;
import com.example.api_get_post_berita.adapter.BeritaAdapter;
import com.example.api_get_post_berita.model.Berita;
import com.example.api_get_post_berita.viewmodels.BeritaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Berita> beritaArrayList = new ArrayList<>();
    BeritaAdapter beritaAdapter;
    RecyclerView rvBerita;
    BeritaViewModel beritaViewModel;
    FloatingActionButton fab1;
//    TextView refreshTextView, addTextView;
    List<Berita> beritas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();
    }

    void findViewById(){
        rvBerita = findViewById(R.id.beritaRecyclerView);
        fab1 = findViewById(R.id.fab1);
//        addTextView = (TextView) findViewById(R.id.addTextView);
    }

    private void initData() {
        if (beritaAdapter == null) {
            beritaAdapter = new BeritaAdapter(MainActivity.this, beritaArrayList);
            rvBerita.setLayoutManager(new LinearLayoutManager(this));
            rvBerita.setAdapter(beritaAdapter);
            rvBerita.setItemAnimator(new DefaultItemAnimator());
            rvBerita.setNestedScrollingEnabled(true);
        } else {
            beritaAdapter.notifyDataSetChanged();
        }
        beritaViewModel = ViewModelProviders.of(this).get(BeritaViewModel.class);

        beritaViewModel.init();
        beritaViewModel.getBeritasRepository().observe(this, beritasResponse -> {
            beritas = beritasResponse.getData();
            beritaArrayList.clear();
            beritaArrayList.addAll(beritas);
            beritaAdapter.notifyDataSetChanged();
        });


    }
    private void getListBerita(String page, String limit ){
        beritaViewModel.refresh(page,limit);
        beritaViewModel.getBeritasRepository().observe(this, beritasResponse -> {
            beritas = beritasResponse.getData();
            beritaArrayList.clear();
            beritaArrayList.addAll(beritas);
            beritaAdapter.notifyDataSetChanged();
        });
    }
    void onClickGroup(){
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent( getApplicationContext(), AddActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListBerita("1","20");
    }
}