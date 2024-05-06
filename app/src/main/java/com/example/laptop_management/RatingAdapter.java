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
import java.util.Locale;

public class RatingAdapter extends ArrayAdapter<Rating> {
    public RatingAdapter(@NonNull Context context, int resource, @NonNull List<Rating> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_rating, parent, false);
        TextView tvSelectedRating = convertView.findViewById(R.id.rate_selected);
        Rating rating = this.getItem(position);
        if(rating != null){
            tvSelectedRating.setText(rating.getRating_start());

        }
        return convertView;    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating, parent, false);
        TextView tvRating = convertView.findViewById(R.id.tv_rating);
        Rating rating = this.getItem(position);
        if(rating != null){
            tvRating.setText(rating.getRating_start());

        }
        return convertView;
    }
}
