package com.example.api_get_post_berita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.api_get_post_berita.R;
import com.example.api_get_post_berita.model.Berita;

import java.util.ArrayList;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {
    Context context;
    ArrayList<Berita> beritas;

    public BeritaAdapter(Context context, ArrayList<Berita> beritas) {
        this.context = context;
        this.beritas = beritas;
    }

    @NonNull
    @Override
    public BeritaAdapter.BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_berita, parent, false);
        return new  BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaAdapter.BeritaViewHolder holder, int position) {
        holder.titleTv.setText(beritas.get(position).getTitle());
        holder.categoryTv.setText(beritas.get(position).getCategory());
        Glide.with(holder.itemView)
                .load(beritas.get(position).getUrl())
                .into(holder.photoIv);

//        Picasso.get().load(beritas.get(position).getUrlToImage()).into(holder.nasabahIv);
    }

    @Override
    public int getItemCount() {
        return beritas.size();
    }
    public class BeritaViewHolder extends RecyclerView.ViewHolder{
        TextView titleTv, categoryTv;
        ImageView photoIv;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.titleTextView);
            categoryTv = itemView.findViewById(R.id.categoryTextView);
            photoIv = itemView.findViewById(R.id.photoImageView);
        }
    }
}
