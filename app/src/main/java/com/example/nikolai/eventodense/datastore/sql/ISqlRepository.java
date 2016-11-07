package com.example.nikolai.eventodense.datastore.sql;

import java.util.ArrayList;

/**
 * Created by lennartolsen on 24/10/2016.
 */

public abstract class ISqlRepository<Type> {
    public abstract String getTableName();

    /**
     * Builds the SQL for the database
     * @return sql string
     */
    public abstract String createDBString();

    public abstract Type get(String id);

    public abstract ArrayList<Type> get();

    public abstract Boolean save(Type p);
    public abstract Boolean save(ArrayList<Type> p);

    /**
     * Get limit
     * @param count
     * @return list of models
     */
    public abstract ArrayList<Type> get(int count);

    public abstract int count();
}
