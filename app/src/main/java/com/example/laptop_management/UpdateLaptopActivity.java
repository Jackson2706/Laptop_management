package com.example.laptop_management;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class UpdateLaptopActivity extends AppCompatActivity {

    private Spinner spnBrandData_update;
    private BrandIdAdapter brandIdAdapter_update;
    private EditText laptop_name_update, price_update, display_size_update, chip_information_update, ram_information_update;
    private String laptop_id, laptop_name, brand_id, price, display_size, chip_information, ram_information;
    private Button update_btn, delete_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_laptop);
        spnBrandData_update = findViewById(R.id.spn_brand_id_update);
        laptop_name_update = findViewById(R.id.editTextLaptopName_update);
        price_update = findViewById(R.id.editTextLaptopPrice_update);
        display_size_update = findViewById(R.id.editTextLaptopDisplaySize_update);
        chip_information_update = findViewById(R.id.editTextLaptopChip_update);
        ram_information_update = findViewById(R.id.editTextLaptopRam_update);
        update_btn = findViewById(R.id.btn_update_laptop_infor);
        delete_btn = findViewById(R.id.btn_delete_laptop_infor);

        brandIdAdapter_update = new BrandIdAdapter(this, R.layout.selected_brand, getListBrandData());
        spnBrandData_update.setAdapter(brandIdAdapter_update);
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        ab.setTitle(laptop_name);
        spnBrandData_update.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                brand_id = brandIdAdapter_update.getItem(position).getBrandID();
                Toast.makeText(UpdateLaptopActivity.this, brandIdAdapter_update.getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laptop_name = laptop_name_update.getText().toString().trim();
                price = price_update.getText().toString().trim();
                display_size = display_size_update.getText().toString().trim();
                chip_information = chip_information_update.getText().toString().trim();
                ram_information = ram_information_update.getText().toString().trim();

                MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateLaptopActivity.this);
                myDB.updateLaptopData(laptop_id, laptop_name, Integer.parseInt(brand_id), Integer.parseInt(price), Float.parseFloat(display_size), chip_information, Integer.parseInt(ram_information));
                finish();
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateLaptopActivity.this);
                myDB.deleteAnLaptopData(laptop_id);
                finish();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("laptop_id") && getIntent().hasExtra("laptop_name") &&
                getIntent().hasExtra("brand_id") && getIntent().hasExtra("price") &&
                getIntent().hasExtra("display_size") && getIntent().hasExtra("chip_information") &&
                getIntent().hasExtra("ram_information")){
            laptop_id = getIntent().getStringExtra("laptop_id");
            laptop_name = getIntent().getStringExtra("laptop_name");
            brand_id = getIntent().getStringExtra("brand_id");
            price = getIntent().getStringExtra("price");
            display_size = getIntent().getStringExtra("display_size");
            chip_information = getIntent().getStringExtra("chip_information");
            ram_information = getIntent().getStringExtra("ram_information");

            laptop_name_update.setText(laptop_name);
            price_update.setText(price);
            display_size_update.setText(display_size);
            chip_information_update.setText(chip_information);
            ram_information_update.setText(ram_information);
        }
    }

    private List<BrandData> getListBrandData() {
        List<BrandData> list = new ArrayList<>();
        MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateLaptopActivity.this);
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