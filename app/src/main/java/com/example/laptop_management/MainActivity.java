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

        categoryListViewAdapter = new CategoryListViewAdapter(listCategory);
        categoryListView = findViewById(R.id.taskList);
        categoryListView.setAdapter(categoryListViewAdapter);

        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(MainActivity.this, "Laptop Management !", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(MainActivity.this, InsertData.class);

                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "Laptop Brand Management!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LaptopBrandActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;

                }
            }
        });
    }
}