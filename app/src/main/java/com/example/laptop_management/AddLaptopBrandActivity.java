package com.example.laptop_management;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AddLaptopBrandActivity extends AppCompatActivity {
    private EditText et_brand_name;
    private Spinner spnRating;
    private Button add_btn;
    private RatingAdapter ratingAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laptop_brand);
        et_brand_name = findViewById(R.id.editTextLaptopBrand);
        add_btn = findViewById(R.id.btn_add_brand);

        spnRating = findViewById(R.id.spn_rating);
        ratingAdapter = new RatingAdapter(this, R.layout.selected_rating, getListRating());
        spnRating.setAdapter(ratingAdapter);

        final int[] rating_star = new int[1];
        spnRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                rating_star[0] = i;
                Toast.makeText(AddLaptopBrandActivity.this, ratingAdapter.getItem(i).getRating_start(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                rating_star[0] = 0;
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(AddLaptopBrandActivity.this);
                myDB.addBrandData(et_brand_name.getText().toString().trim(), rating_star[0]);
                finish();
            }
        });
    }

    public static List<Rating> getListRating(){
        List<Rating> list = new ArrayList<>();
        list.add(new Rating("0 star"));
        list.add(new Rating("1 star"));
        list.add(new Rating("2 star"));
        list.add(new Rating("3 star"));
        list.add(new Rating("4 star"));
        list.add(new Rating("5 star"));
        return list;
    }
}

