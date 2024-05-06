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
}
