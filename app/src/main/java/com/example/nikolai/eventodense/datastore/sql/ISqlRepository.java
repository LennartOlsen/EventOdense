package com.example.nikolai.eventodense.datastore.sql;

import android.content.ContentValues;

import com.example.nikolai.eventodense.models.IDataModel;
import com.example.nikolai.eventodense.models.IRepository;

import java.util.ArrayList;

/**
 * Created by lennartolsen on 24/10/2016.
 */

public interface ISqlRepository extends IRepository {
    String getTableName();

    /**
     * Builds the SQL for the database
     * @return sql string
     */
    String createDBString();

    @Override
    IDataModel get(String id);

    @Override
    ArrayList<IDataModel> get();

    @Override
    Boolean save(IDataModel p);
}
