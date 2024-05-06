package com.example.laptop_management;

import android.annotation.SuppressLint;
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

public class UpdateLaptopBrandActivity extends AppCompatActivity {

    private EditText et_brand_name_update;
    private Spinner spnRating_update;
    private Button btn_update, btn_delete;
    private RatingAdapter ratingAdapter;
    private String brand_id, brand_name, brand_star_rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_laptop_brand);
        et_brand_name_update = findViewById(R.id.editTextLaptopBrand_update);
        btn_update = findViewById(R.id.btn_update_brand);
        btn_delete = findViewById(R.id.btn_delete_brand);
        spnRating_update = findViewById(R.id.spn_rating_update);
        ratingAdapter = new RatingAdapter(this, R.layout.selected_rating, AddLaptopBrandActivity.getListRating());
        spnRating_update.setAdapter(ratingAdapter);

        final int[] rating_star = new int[1];
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        ab.setTitle(brand_name);
        spnRating_update.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rating_star[0] = i;
                Toast.makeText(UpdateLaptopBrandActivity.this, ratingAdapter.getItem(i).getRating_start(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                rating_star[0] = 0;
            }
        });


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brand_name = et_brand_name_update.getText().toString().trim();
                MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateLaptopBrandActivity.this);
                myDB.updateBrandData(brand_id, brand_name, String.valueOf(rating_star[0]));
                finish();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(UpdateLaptopBrandActivity.this);
                myDB.deleteAnBrandData(brand_id);
                finish();
            }
        });



    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("brand_id") && getIntent().hasExtra("brand_name")
        && getIntent().hasExtra("brand_star_rate")){
            brand_id =getIntent().getStringExtra("brand_id");
            brand_name = getIntent().getStringExtra("brand_name");
            brand_star_rate = getIntent().getStringExtra("brand_star_rate");
            et_brand_name_update.setText(brand_name);
            spnRating_update.setSelection(Integer.parseInt(brand_star_rate));

        } else {
            Toast.makeText(this, "No data...", Toast.LENGTH_SHORT).show();
        }
    }
}