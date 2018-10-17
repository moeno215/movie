package com.example.maulana.movie.UI.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maulana.movie.R;
import com.example.maulana.movie.UI.DetailActivity;
import com.example.maulana.movie.UI.MainActivity;
import com.example.maulana.movie.UI.model.ResultsItem;
import com.example.maulana.movie.UI.rest.Constant;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<ResultsItem> resultsItems;
    Context context;

    public MyAdapter(List<ResultsItem> resultsItems, MainActivity mainActivity) {
        this.resultsItems = resultsItems;
        this.context = mainActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.listitem, null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nama.setText(resultsItems.get(position).getTitle());
        holder.keterangan.setText(resultsItems.get(position).getOverview());
        holder.tglRilis.setText(resultsItems.get(position).getReleaseDate());

        Glide.with(context)
                .load(Constant.URL_IMAGE + resultsItems.get(position).getPosterPath())
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                view.getContext().startActivity(intent);}

        });
    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView nama, keterangan, tglRilis;

        public MyViewHolder(View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.tittle);
            keterangan = itemView.findViewById(R.id.desc);
            tglRilis = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.img);

        }

    }



}


