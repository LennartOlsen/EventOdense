package com.example.nikolai.eventodense.models.Point;

import android.util.Log;

import com.example.nikolai.eventodense.datastore.http.ApiClient;
import com.example.nikolai.eventodense.datastore.http.ApiInterface;
import com.example.nikolai.eventodense.datastore.http.IHttpRepository;
import com.example.nikolai.eventodense.models.IDataModel;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lennartolsen on 24/10/2016.
 */

public class PointHttpRepository extends IHttpRepository<Point> {

    ApiInterface api;

    public PointHttpRepository(){
        api = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public Point get(String id) {
        return null;
    }

    @Override
    public void get(Callback<ArrayList<Point>> cb) {
    }

    @Override
    public void save(Point p, Callback<Point[]> cb) {}

    @Override
    public void save(ArrayList<Point> p, Callback<ArrayList<Point>> cb) {
        Call<ArrayList<Point>> call = api.createPoints(p);
        call.enqueue(cb);
    }


    public int count() {
        return 0;
    }

    private String FromModelToJson(IDataModel model){
        Gson jsoner = new Gson();
        Point p = (Point) model;
        String json = jsoner.toJson(p);
        return json;
    }

    private String FromArrayListToJson(ArrayList<IDataModel> models){
        Gson jsoner = new Gson();
        String json = jsoner.toJson(models);
        return json;
    }
}
