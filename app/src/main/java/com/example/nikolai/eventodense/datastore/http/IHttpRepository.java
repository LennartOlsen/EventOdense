package com.example.nikolai.eventodense.datastore.http;

import java.util.ArrayList;

import retrofit2.Callback;

/**
 * Created by lennartolsen on 24/10/2016.
 */

public abstract class IHttpRepository<Type> {

    public abstract Type get(String id);

    public abstract void get(Callback<ArrayList<Type>> cb);

    public abstract void save(Type p, Callback<Type[]> cb);

    public abstract void save(ArrayList<Type> p, Callback<ArrayList<Type>> cb);
}
