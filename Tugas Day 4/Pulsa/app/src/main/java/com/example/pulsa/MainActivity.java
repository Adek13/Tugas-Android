package com.example.pulsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pulsa.ViewModels.PulsaViewModel;
import com.example.pulsa.adapter.ProductAdapter;
import com.example.pulsa.model.Product;
import com.example.pulsa.R;
import com.example.pulsa.model.Pulsa;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> productArrayList = new ArrayList<>();
    List<Product> products;
    ProductAdapter productAdapter;
    PulsaViewModel pulsaViewModel;

    @BindView(R.id.hargaTextView)
    TextView hargaTextView;

    @BindView(R.id.codeTextView)
    TextView codeTextView;

    @BindView(R.id.nomorEditText)
    TextInputEditText nomorEditText;

    @BindView(R.id.productRv)
    RecyclerView rvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        if (productAdapter == null) {
            productAdapter = new ProductAdapter(MainActivity.this, productArrayList, hargaTextView, codeTextView);
            rvProduct.setLayoutManager(new GridLayoutManager(this, 2));
            rvProduct.setAdapter(productAdapter);
            rvProduct.setItemAnimator(new DefaultItemAnimator());
            rvProduct.setNestedScrollingEnabled(true);
        } else {
            productAdapter.notifyDataSetChanged();
        }
        pulsaViewModel = ViewModelProviders.of(this).get(PulsaViewModel.class);

        pulsaViewModel.init();
        pulsaViewModel.getPulsaRepository().observe(this, productResponse -> {
            products = productResponse.getData();
            productArrayList.clear();
            productArrayList.addAll(products);
            productAdapter.notifyDataSetChanged();
        });


    }
    private void getListBerita(String page, String limit ){
        PulsaViewModel.refresh(page,limit);
        PulsaViewModel.getPulsaRepository().observe(this, productResponse -> {
            products = productResponse.getData();
            productArrayList.clear();
            productArrayList.addAll(products);
            productAdapter.notifyDataSetChanged();
        });
    }

    @OnClick(R.id.bayarButton)
    public void bayar(){
        Pulsa pulsaPayload = new Pulsa();
        pulsaPayload.setCode(codeTextView.getText().toString());
        pulsaPayload.setPhone_number(nomorEditText.getText().toString());

        pulsaViewModel.postPulsa(pulsaPayload).observe(this, pulsaResponse -> {
            Toast.makeText(getApplicationContext(), "Sukses!", Toast.LENGTH_SHORT).show();
        });
    }
}