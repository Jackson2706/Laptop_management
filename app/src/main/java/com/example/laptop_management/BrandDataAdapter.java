package com.example.laptop_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BrandDataAdapter extends RecyclerView.Adapter<BrandDataAdapter.MyViewHolder> {
    private Context context;
    private ArrayList brand_id, brand_name, brand_start_rate;

    BrandDataAdapter(Context context, ArrayList brand_id, ArrayList brand_name, ArrayList brand_start_rate){
        this.context = context;
        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.brand_start_rate = brand_start_rate;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.brand_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.brand_id_txt.setText(String.valueOf(brand_id.get(position)));
        holder.brand_name_txt.setText(String.valueOf(brand_name.get(position)));
        holder.brand_star_rate_txt.setText(String.valueOf(brand_start_rate.get(position)) + " star(s)");

    }

    @Override
    public int getItemCount() {
        return brand_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView brand_id_txt, brand_name_txt, brand_star_rate_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            brand_id_txt = itemView.findViewById(R.id.brand_id_txt);
            brand_name_txt = itemView.findViewById(R.id.brand_name_txt);
            brand_star_rate_txt = itemView.findViewById(R.id.brand_rate_txt);
        }
    }
}
