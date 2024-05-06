package com.example.laptop_management;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BrandDataAdapter extends RecyclerView.Adapter<BrandDataAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<String> brand_id, brand_name, brand_start_rate;
    private int position;

    BrandDataAdapter(Activity activity, Context context, ArrayList brand_id, ArrayList brand_name, ArrayList brand_start_rate){
        this.context = context;
        this.activity = activity;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;
        holder.brand_id_txt.setText(String.valueOf(brand_id.get(position)));
        holder.brand_name_txt.setText(String.valueOf(brand_name.get(position)));
        holder.brand_star_rate_txt.setText(String.valueOf(brand_start_rate.get(position)) + " star(s)");
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateLaptopBrandActivity.class);
                intent.putExtra("brand_id", String.valueOf(brand_id.get(position)));
                intent.putExtra("brand_name", String.valueOf(brand_name.get(position)));
                intent.putExtra("brand_star_rate", String.valueOf(brand_start_rate.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return brand_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView brand_id_txt, brand_name_txt, brand_star_rate_txt;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            brand_id_txt = itemView.findViewById(R.id.brand_id_txt);
            brand_name_txt = itemView.findViewById(R.id.brand_name_txt);
            brand_star_rate_txt = itemView.findViewById(R.id.brand_rate_txt);
            linearLayout = itemView.findViewById(R.id.brand_row);
        }
    }
}
