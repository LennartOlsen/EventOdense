package com.example.nikolai.eventodense;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Console;

/**
 * Created by Steffen on 16-10-2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "location.db";
    private static final String TABLE_NAME = "location_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "LOCATION";
    public static final String COL_3 = "TIME STAMP";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, LOCATION TEXT, TIME STAMP INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, location);
        contentValues.put(COL_3,System.currentTimeMillis() / 1000L);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            Log.e("ERROR", "An error occured!");
            return false;
        } else{
            return true;
        }
    }
}
