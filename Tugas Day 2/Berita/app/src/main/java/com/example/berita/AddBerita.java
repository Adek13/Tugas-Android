package com.example.berita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berita.adapter.BeritaAdapter;
import com.example.berita.model.Berita;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddBerita extends AppCompatActivity {

    EditText titleEditText, categoryEditText, urlImageEditText;
    Berita berita;
    BeritaAdapter beritaAdapter;
    MainActivity main;
    public List<Berita> listBerita = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_berita);
        listBerita = (List<Berita>) getIntent().getSerializableExtra("list");
        findViewById();
    }

    void findViewById(){
        titleEditText = findViewById(R.id.titleEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
        urlImageEditText = findViewById(R.id.urlImageEditText);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.save:
                String title = titleEditText.getText().toString();
                String category = categoryEditText.getText().toString();
                String urlImage = urlImageEditText.getText().toString();
                Berita berita = new Berita();
                berita.setTitle(title);
                berita.setCategory(category);
                berita.setUrlImage(urlImage);
                listBerita.add(berita);
                Intent intent = new Intent(AddBerita.this, MainActivity.class);
                intent.putExtra("list", (Serializable) listBerita);
                startActivity(intent);
                finish();
                return true;
            default:
                Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
        }
        return (super.onOptionsItemSelected(item));
    }
}