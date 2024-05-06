package com.example.laptop_management;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class LaptopBrandActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton add_button;

    private MyDataBaseHelper myDB;
    ArrayList<String> brand_id, brand_name, brand_star_rate;
    BrandDataAdapter brandDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_brand);

        recyclerView = findViewById(R.id.laptop_brand_recyclerView);
        add_button = findViewById(R.id.laptop_brand_add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaptopBrandActivity.this, AddLaptopBrandActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDataBaseHelper(LaptopBrandActivity.this);
        brand_id = new ArrayList<>();
        brand_name = new ArrayList<>();
        brand_star_rate = new ArrayList<>();
        storeBrandDataInArrays();
        brandDataAdapter = new BrandDataAdapter(LaptopBrandActivity.this, brand_id, brand_name, brand_star_rate);
        recyclerView.setAdapter(brandDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(LaptopBrandActivity.this));

    }
    void storeBrandDataInArrays(){
        Cursor cursor = myDB.readAllBrandNData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data...", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                brand_id.add(cursor.getString(0));
                brand_name.add(cursor.getString(1));
                brand_star_rate.add(cursor.getString(2));
            }
        }
    }
}
