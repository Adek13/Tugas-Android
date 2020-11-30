package com.example.berita;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.berita.adapter.BeritaAdapter;
import com.example.berita.model.Berita;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView beritaListView;
    BeritaAdapter beritaAdapter;
    Button addButton;
    public List<Berita> listBerita = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();
        getIntentChange();
        List<Berita> lists = (List<Berita>) getIntent().getSerializableExtra("list");
        if (lists!=null){
            listBerita = lists;
            initData();
        }
    }

    void findViewById(){
        beritaListView = (ListView) findViewById(R.id.beritaListView);
        addButton = (Button) findViewById(R.id.addButton);
    }

    void initData(){
        beritaAdapter = new BeritaAdapter(getApplicationContext(),listBerita);
        beritaListView.setAdapter(beritaAdapter);
        beritaAdapter.notifyDataSetChanged();
    }

    void onClickGroup(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddBerita.class);
                intent.putExtra("list", (Serializable) listBerita);
                startActivity(intent);
            }
        });
    }

    void getIntentChange(){
        if (getIntent().getStringExtra("title") != null){
            Berita newBerita = new Berita();
            newBerita.setTitle("title 1");
            newBerita.setCategory("sports");
            newBerita.setUrlImage("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg");
            listBerita.add(newBerita);
            beritaAdapter.notifyDataSetChanged();
        }
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        listBerita = (List<Berita>) getIntent().getSerializableExtra("list");
        Toast.makeText(getApplicationContext(), listBerita.get(1).getTitle(), Toast.LENGTH_SHORT).show();
//        beritaAdapter = new BeritaAdapter(getApplicationContext(),listBerita);
//        beritaListView.setAdapter(beritaAdapter);
//        beritaAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.refresh:
//                Berita newBerita = new Berita();
//                newBerita.setTitle("title 1");
//                newBerita.setCategory("sports");
//                newBerita.setUrlImage("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg");
//                listBerita.add(newBerita);
//
//                newBerita = new Berita();
//                newBerita.setTitle("title 2");
//                newBerita.setCategory("sportss");
//                newBerita.setUrlImage("https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg");
//                listBerita.add(newBerita);
//                beritaAdapter.notifyDataSetChanged();
                return(true);
            default:
                Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
        }
        return (super.onOptionsItemSelected(item));
    }


}