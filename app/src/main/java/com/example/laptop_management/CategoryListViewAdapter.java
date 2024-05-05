package com.example.laptop_management;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryListViewAdapter extends BaseAdapter {
    final ArrayList<CategoryData> listCategory;

    public CategoryListViewAdapter(ArrayList<CategoryData> listCategory) {
        this.listCategory = listCategory;
    }

    @Override
    public int getCount() {
        return listCategory.size();
    }

    @Override
    public Object getItem(int position) {
        return listCategory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCategory;
        if (convertView == null){
            viewCategory = View.inflate(parent.getContext(), R.layout.obj_row, null);
        } else {
            viewCategory = convertView;
        }
        CategoryData categoryData = (CategoryData) getItem(position);
        ((ImageView) viewCategory.findViewById(R.id.logo)).setImageResource(listCategory.get(position).getImage());
        ((TextView) viewCategory.findViewById(R.id.text1)).setText(listCategory.get(position).getTitle());
        ((TextView) viewCategory.findViewById(R.id.text2)).setText(listCategory.get(position).getDesc());
        return viewCategory;
    }
}
