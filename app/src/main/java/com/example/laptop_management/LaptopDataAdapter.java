package com.example.laptop_management;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopDataAdapter extends RecyclerView.Adapter<LaptopDataAdapter.MyViewHolder> {

    Context context;
    ArrayList laptop_ids, laptop_names, brand_ids, price_list, display_size_list, chip_information_list, ram_information_list;

    public LaptopDataAdapter(Context context,
                             ArrayList laptop_ids,
                             ArrayList laptop_names,
                             ArrayList brand_ids,
                             ArrayList price_list,
                             ArrayList display_size_list,
                             ArrayList chip_information_list,
                             ArrayList ram_information_list) {
        this.context = context;
        this.laptop_ids = laptop_ids;
        this.laptop_names = laptop_names;
        this.brand_ids = brand_ids;
        this.price_list = price_list;
        this.display_size_list = display_size_list;
        this.chip_information_list = chip_information_list;
        this.ram_information_list = ram_information_list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.laptop_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.laptop_id.setText(String.valueOf(laptop_ids.get(position)));
        holder.laptop_name.setText(String.valueOf(laptop_names.get(position)));
        MyDataBaseHelper myDB = new MyDataBaseHelper(context);

        Cursor cursor = myDB.readBrandById(String.valueOf(brand_ids.get(position)));
        String brand_name_txt = "";
        if (cursor != null) {
            brand_name_txt = cursor.getString(1) + " - " + cursor.getString(2) + " star(s)";
        } else {
            brand_name_txt = String.valueOf(brand_ids.get(position));
        }
        holder.brand_name.setText(brand_name_txt);

        long price_long = Long.parseLong(String.valueOf(price_list.get(position)));
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String price_money_format = decimalFormat.format(price_long);
        holder.prices.setText( price_money_format + " VND");
    }

    @Override
    public int getItemCount() {
        return brand_ids.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView laptop_id, laptop_name, brand_name, prices;
        LinearLayout linearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            laptop_id = itemView.findViewById(R.id.laptop_id_txt);
            laptop_name = itemView.findViewById(R.id.laptop_name_txt);
            brand_name = itemView.findViewById(R.id.brand_name_txt);
            prices = itemView.findViewById(R.id.price_txt);
        }
    }
}
