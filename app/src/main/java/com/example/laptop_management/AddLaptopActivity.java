package com.example.laptop_management;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AddLaptopActivity extends AppCompatActivity {
    private Spinner spnBrandData;
    private BrandIdAdapter brandIdAdapter;
    private EditText laptop_name, price, display_size, chip_information, ram_information;
    private Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laptop);

        laptop_name = findViewById(R.id.editTextLaptopName);
        price = findViewById(R.id.editTextLaptopPrice);
        display_size = findViewById(R.id.editTextLaptopDisplaySize);
        chip_information = findViewById(R.id.editTextLaptopChip);
        ram_information = findViewById(R.id.editTextLaptopRam);
        add_btn = findViewById(R.id.btn_add_laptop_infor);

        spnBrandData = findViewById(R.id.spn_brand_id);
        brandIdAdapter = new BrandIdAdapter(this, R.layout.selected_brand, getListBrandData());
        spnBrandData.setAdapter(brandIdAdapter);

        final int[] brand_id = new int[1];
        spnBrandData.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                brand_id[0] = Integer.parseInt(brandIdAdapter.getItem(position).getBrandID());
                Toast.makeText(AddLaptopActivity.this, brandIdAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String laptop_name_txt = laptop_name.getText().toString().trim();
                int price_txt = Integer.parseInt(price.getText().toString().trim());
                float display_size_txt = Float.parseFloat(display_size.getText().toString().trim());
                String chip_information_txt = chip_information.getText().toString().trim();
                int ram_information_txt = Integer.parseInt(ram_information.getText().toString().trim());

                MyDataBaseHelper myDB = new MyDataBaseHelper(AddLaptopActivity.this);
                myDB.addLaptopData(laptop_name_txt, brand_id[0], price_txt, display_size_txt, chip_information_txt, ram_information_txt);
                finish();
            }
        });

    }

    private List<BrandData> getListBrandData() {
        List<BrandData> list = new ArrayList<>();
        MyDataBaseHelper myDB = new MyDataBaseHelper(AddLaptopActivity.this);
        Cursor cursor = myDB.readAllBrandNData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data...", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                list.add(new BrandData(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
        return list;
    }
}