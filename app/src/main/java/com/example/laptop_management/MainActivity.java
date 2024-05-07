package com.example.laptop_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<CategoryData> listCategory;
    CategoryListViewAdapter categoryListViewAdapter;
    ListView categoryListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCategory = new ArrayList<>();
        listCategory.add(new CategoryData(R.drawable.ic_launcher_foreground, "Laptop Management", "Create/Read/Update/Delete Latop data"));
        listCategory.add(new CategoryData(R.drawable.ic_launcher_foreground, "Laptop Brand Management", "Create/Read/Update/Delete Laptop Brand data"));
        listCategory.add(new CategoryData(R.drawable.ic_launcher_foreground, "Display laptops by brand", "Create/Read/Update/Delete Laptop data"));
        listCategory.add(new CategoryData(R.drawable.ic_launcher_foreground, "List laptops with screen size larger than 14 inches and rated at least 3 stars", "Create/Read/Update/Delete Laptop data"));
        categoryListViewAdapter = new CategoryListViewAdapter(listCategory);
        categoryListView = findViewById(R.id.taskList);
        categoryListView.setAdapter(categoryListViewAdapter);

        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(MainActivity.this, "Laptop Management !", Toast.LENGTH_SHORT).show();
                        Intent intent_1 = new Intent(MainActivity.this, LaptopActivity.class);
                        intent_1.putExtra("Request", "0");

                        startActivity(intent_1);


                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "Laptop Brand Management!", Toast.LENGTH_SHORT).show();
                        Intent intent_2 = new Intent(MainActivity.this, LaptopBrandActivity.class);
                        startActivity(intent_2);
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, "Display laptops by brand.!", Toast.LENGTH_SHORT).show();
                        Intent intent_3 = new Intent(MainActivity.this, LaptopActivity.class);
                        intent_3.putExtra("Request", "1");
                        startActivity(intent_3);
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, "List laptops with screen size larger than 14 inches and rated at least 3 stars", Toast.LENGTH_SHORT).show();
                        Intent intent_4 = new Intent(MainActivity.this, LaptopActivity.class);
                        intent_4.putExtra("Request", "2");
                        startActivity(intent_4);
                        break;
                    default:
                        break;

                }
            }
        });
    }
}