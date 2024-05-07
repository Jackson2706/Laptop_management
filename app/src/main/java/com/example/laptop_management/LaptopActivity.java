package com.example.laptop_management;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class LaptopActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LaptopDataAdapter laptopDataAdapter;
    private FloatingActionButton add_btn;

    MyDataBaseHelper myDB;
    ArrayList<String> laptop_ids, laptop_names, brand_ids, prices, display_size_list, chip_information_list, ram_information_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);

        recyclerView = findViewById(R.id.laptop_recyclerView);
        add_btn = findViewById(R.id.laptop_add_button);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaptopActivity.this, AddLaptopActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        myDB = new MyDataBaseHelper(LaptopActivity.this);
        laptop_ids = new ArrayList<>();
        laptop_names = new ArrayList<>();
        brand_ids = new ArrayList<>();
        prices = new ArrayList<>();
        display_size_list = new ArrayList<>();
        chip_information_list = new ArrayList<>();
        ram_information_list = new ArrayList<>();
        switch (Objects.requireNonNull(getIntent().getStringExtra("Request"))){
            case "0":
                storeAllLaptopDataInArray();
                break;
            case "1":
                storeLaptopDataByBrandInArray();
                break;

            case "2":
                storeLaptopData3starInArray();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + getIntent().hasExtra("Request"));
        }
        laptopDataAdapter = new LaptopDataAdapter(LaptopActivity.this, this, laptop_ids, laptop_names, brand_ids, prices, display_size_list, chip_information_list, ram_information_list);
        recyclerView.setAdapter(laptopDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(LaptopActivity.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeAllLaptopDataInArray(){
        Cursor cursor = myDB.readAllLaptopData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data...", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                laptop_ids.add(cursor.getString(0));
                laptop_names.add(cursor.getString(1));
                brand_ids.add(cursor.getString(2));
                prices.add(cursor.getString(3));
                display_size_list.add(cursor.getString(4));
                chip_information_list.add(cursor.getString(5));
                ram_information_list.add(cursor.getString(6));
            }
        }

    }

    void storeLaptopDataByBrandInArray(){
        Cursor cursor = myDB.readAllLaptopByBrandData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data...", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                laptop_ids.add(cursor.getString(0));
                laptop_names.add(cursor.getString(1));
                brand_ids.add(cursor.getString(2));
                prices.add(cursor.getString(3));
                display_size_list.add(cursor.getString(4));
                chip_information_list.add(cursor.getString(5));
                ram_information_list.add(cursor.getString(6));
            }
        }

    }

    void storeLaptopData3starInArray(){
        Cursor cursor = myDB.readFilteredLaptopData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data...", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                laptop_ids.add(cursor.getString(0));
                laptop_names.add(cursor.getString(1));
                brand_ids.add(cursor.getString(2));
                prices.add(cursor.getString(3));
                display_size_list.add(cursor.getString(4));
                chip_information_list.add(cursor.getString(5));
                ram_information_list.add(cursor.getString(6));
            }
        }

    }
}