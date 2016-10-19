package com.example.nikolai.eventodense.models;

import android.content.ContentValues;

/**
 * Created by lennartolsen on 19/10/2016.
 */

public interface ISqlDataModel {

    public String getTableName();

    /**
     * Builds the SQL for the database
     * @return sql string
     */
    public String createDBString();
    public ContentValues getSQLContentValues();
}
