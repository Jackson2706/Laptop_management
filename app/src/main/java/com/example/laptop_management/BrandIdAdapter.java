package com.example.laptop_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BrandIdAdapter extends ArrayAdapter<BrandData> {
    public BrandIdAdapter(@NonNull Context context, int resource, @NonNull List<BrandData> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_brand, parent, false);
        TextView tvSelectedBrand = convertView.findViewById(R.id.brand_id_selected);

        BrandData brandData = this.getItem(position);
        if (brandData != null){
            tvSelectedBrand.setText(brandData.toString());
        }

        return convertView;    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brand, parent, false);
        TextView tvBrand = convertView.findViewById(R.id.tv_brand_id);

        BrandData brandData = this.getItem(position);
        if (brandData != null){
            tvBrand.setText(brandData.toString());
        }

        return convertView;
    }
}
