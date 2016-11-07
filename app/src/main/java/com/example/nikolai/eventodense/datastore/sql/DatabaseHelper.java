package com.example.nikolai.eventodense.datastore.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nikolai.eventodense.models.Point.PointSQLRepository;

/**
 * Created by Steffen on 16-10-2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "location.db";
    private static final String TABLE_NAME = "location_table";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getHelper(Context context)
    {
        if (instance == null)
            instance = new DatabaseHelper(context);

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        PointSQLRepository pRepos = new PointSQLRepository();
        pRepos.createDBString();
        db.execSQL(pRepos.createDBString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(ISqlRepository repos, ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(repos.getTableName(), null, cv);

        if(result == -1){
            Log.e(TAG, "An error occured! With the code : " + result);
            return false;
        } else{
            return true;
        }
    }

    public Cursor getModel(ISqlRepository repos){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery("SELECT * FROM " + repos.getTableName() + " ORDER BY timestamp ASC" ,null);
        resultSet.moveToFirst();
        return resultSet;
    }

    public Cursor getModel(ISqlRepository repos, String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery("SELECT * FROM " + repos.getTableName() + " WHERE id = " + id  ,null);
        resultSet.moveToFirst();
        return resultSet;
    }

    public Cursor getModel(ISqlRepository repos, int limit){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.rawQuery("SELECT * FROM " + repos.getTableName() + " ORDER BY timestamp ASC" + " LIMIT " + limit ,null);
        resultSet.moveToFirst();
        return resultSet;
    }

    public boolean deleteModel(ISqlRepository repos, int limit){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(repos.getTableName(), "id in (SELECT id FROM " + repos.getTableName() + " ORDER BY timestamp ASC" + " LIMIT "+ limit + ")", new String[]{});

        if(result != limit){
            Log.e(TAG, "Delete did not go as expeceted, deleted : " + result + " rows");
            return false;
        } else{
            return true;
        }
    }
}
