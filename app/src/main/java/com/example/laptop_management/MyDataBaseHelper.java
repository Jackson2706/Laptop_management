package com.example.laptop_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "LaptopManagement.db";
    private static final int DATABASE_VERSION = 1;
    private static final String LAPTOP_BRAND_TABLE_NAME = "LAPTOP_BRAND";
    private static final String COLUMN_LAPTOP_BRAND_ID = "_id";
    private static final String COLUMN_LAPTOP_BRAND_NAME = "_brand_name";
    private static final String COLUMN_LAPTOP_BRAND_RATING  = "_rating";

    private static final String LAPTOP_TABLE_NAME = "LAPTOP";
    private static final String COLUMN_LAPTOP_ID = "_id";
    private static final String COLUMN_LAPTOP_NAME = "_laptop_name";
    private static final String COLUMN_BRAND_ID = "_brand_id";
    private static final String COLUMN_PRICING = "_price";
    private static final String COLUMN_DISPLAY_SIZE = "_display_size";
    private static final String COLUMN_CHIP = "_chip";
    private static final String COLUMN_RAM = "_ram";
    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + LAPTOP_BRAND_TABLE_NAME +
                        " (" + COLUMN_LAPTOP_BRAND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_LAPTOP_BRAND_NAME + " TEXT, " +
                        COLUMN_LAPTOP_BRAND_RATING + " INTEGER);";
        db.execSQL(query);

        query =
                "CREATE TABLE " + LAPTOP_TABLE_NAME +
                        " ( " + COLUMN_LAPTOP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_LAPTOP_NAME + " TEXT, " +
                        COLUMN_BRAND_ID + " INTEGER, " +
                        COLUMN_PRICING + " INTEGER, " +
                        COLUMN_DISPLAY_SIZE + " REAL, " +
                        COLUMN_CHIP + " TEXT, " +
                        COLUMN_RAM + " INTEGER, " +
                        "FOREIGN KEY (" + COLUMN_BRAND_ID + ") REFERENCES " + LAPTOP_BRAND_TABLE_NAME + "(" + COLUMN_LAPTOP_BRAND_ID + ") ON DELETE CASCADE);";


        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LAPTOP_BRAND_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LAPTOP_TABLE_NAME);
        onCreate(db);
    }

    void addBrandData(String brand_name, int rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LAPTOP_BRAND_NAME, brand_name);
        cv.put(COLUMN_LAPTOP_BRAND_RATING, rating);

        long result = db.insert(LAPTOP_BRAND_TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed to add laptop brand data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successed to add laptop brand data", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllBrandNData(){
        String query = "SELECT * FROM " + LAPTOP_BRAND_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return  cursor;
    }

    Cursor readBrandById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + LAPTOP_BRAND_TABLE_NAME + " WHERE " + COLUMN_LAPTOP_BRAND_ID + " = ?";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, new String[]{id});
            cursor.moveToFirst(); // Di chuyển con trỏ đến hàng đầu tiên (nếu có)
        }
        return cursor;
    }




    void updateBrandData(String brand_id, String brand_name, String brand_star_rate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LAPTOP_BRAND_NAME, brand_name);
        cv.put(COLUMN_LAPTOP_BRAND_RATING, brand_star_rate);

        long results = db.update(LAPTOP_BRAND_TABLE_NAME, cv, COLUMN_LAPTOP_BRAND_ID + "=?", new String[]{brand_id});
        if (results == -1){
            Toast.makeText(context, "Failed to update laptop brand data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successed to update laptop brand data", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAnBrandData(String brand_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(LAPTOP_BRAND_TABLE_NAME, COLUMN_LAPTOP_BRAND_ID + "=?",  new String[]{brand_id});
        if (result == -1){
            Toast.makeText(context, "Failed to delete laptop brand data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success to delete laptop brand data", Toast.LENGTH_SHORT).show();
        }
    }

    void addLaptopData(String laptop_name, int brand_id, int price, float display_size, String chip_information, int ram_information){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LAPTOP_NAME, laptop_name);
        cv.put(COLUMN_BRAND_ID, brand_id);
        cv.put(COLUMN_PRICING, price);
        cv.put(COLUMN_DISPLAY_SIZE, display_size);
        cv.put(COLUMN_CHIP, chip_information);
        cv.put(COLUMN_RAM, ram_information);

        long result = db.insert(LAPTOP_TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed to add laptop data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success to add laptop data", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllLaptopData(){
        String query = "SELECT * FROM " + LAPTOP_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return  cursor;
    }

    void updateLaptopData(String laptop_id, String laptop_name, int brand_id, int price, float display_size, String chip_information, int ram_information){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LAPTOP_NAME, laptop_name);
        cv.put(COLUMN_BRAND_ID, brand_id);
        cv.put(COLUMN_PRICING, price);
        cv.put(COLUMN_DISPLAY_SIZE, display_size);
        cv.put(COLUMN_CHIP, chip_information);
        cv.put(COLUMN_RAM, ram_information);

        long results = db.update(LAPTOP_TABLE_NAME, cv, COLUMN_LAPTOP_ID + "=?", new String[]{laptop_id});
        if (results == -1){
            Toast.makeText(context, "Failed to update laptop data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successed to update laptop data", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAnLaptopData(String laptop_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(LAPTOP_TABLE_NAME, COLUMN_LAPTOP_ID + "=?",  new String[]{laptop_id});
        if (result == -1){
            Toast.makeText(context, "Failed to delete laptop data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success to delete laptop data", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllLaptopByBrandData(){
        String query = "SELECT * FROM " + LAPTOP_TABLE_NAME + " ORDER BY " + COLUMN_BRAND_ID;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return  cursor;
    }

    public Cursor readFilteredLaptopData() {
        String query =
                "SELECT " + LAPTOP_TABLE_NAME + ".*" +
                        " FROM " + LAPTOP_TABLE_NAME +
                        " JOIN " + LAPTOP_BRAND_TABLE_NAME +
                        " ON " + LAPTOP_TABLE_NAME + "." + COLUMN_BRAND_ID + " = " + LAPTOP_BRAND_TABLE_NAME + "." + COLUMN_LAPTOP_BRAND_ID +
                        " WHERE " + LAPTOP_TABLE_NAME + "." + COLUMN_DISPLAY_SIZE + " > 14 AND " + LAPTOP_BRAND_TABLE_NAME + "." + COLUMN_LAPTOP_BRAND_RATING + " > 3";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return  cursor;
    }

}
