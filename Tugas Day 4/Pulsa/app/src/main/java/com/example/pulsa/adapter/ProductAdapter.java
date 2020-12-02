package com.example.pulsa.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pulsa.MainActivity;
import com.example.pulsa.model.Product;
import com.example.pulsa.R;
import com.example.pulsa.model.Pulsa;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    ArrayList<Product> product;
    TextView hargaTextView, codeTextView;

    public ProductAdapter(Context context, ArrayList<Product> product, TextView hargaTextView, TextView codeTextView) {
        this.context = context;
        this.product = product;
        this.hargaTextView = hargaTextView;
        this.codeTextView = codeTextView;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pulsa, parent, false);
        return new  ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        holder.nominalTv.setText(product.get(position).getNominal().toString());
        holder.itemCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.itemCv.isChecked()){
                    holder.itemCv.setChecked(false);

                }else{
                    holder.itemCv.setChecked(true);
                    hargaTextView.setText(product.get(position).getNominal().toString());
                    codeTextView.setText(product.get(position).getCode());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return product.size();
    }
    public class ProductViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.nominalTv) TextView nominalTv;
        @BindView(R.id.itemCardView) MaterialCardView itemCv;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}