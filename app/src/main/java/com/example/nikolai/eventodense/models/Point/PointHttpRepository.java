package com.example.nikolai.eventodense.models.Point;

import android.util.Log;

import com.example.nikolai.eventodense.datastore.http.IHttpRepository;
import com.example.nikolai.eventodense.models.IDataModel;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by lennartolsen on 24/10/2016.
 */

public class PointHttpRepository implements IHttpRepository {
    @Override
    public String getEndpoint() {
        return "/points";
    }

    @Override
    public IDataModel get(String id) {
        return null;
    }

    @Override
    public ArrayList<IDataModel> get() {
        return null;
    }

    @Override
    public Boolean save(IDataModel p) {
        return null;
    }

    @Override
    public Boolean save(ArrayList<IDataModel> models) {
        String json = "[";
        for (IDataModel model:
             models) {
            json += FromModelToJson(model) + ",";
        }
        json += "]";
        Log.e("PONITHTTPREPOS", json);
        return true;
    }

    @Override
    public int count() {
        return 0;
    }

    private String FromModelToJson(IDataModel model){
        Gson jsoner = new Gson();
        Point p = (Point) model;
        String json = jsoner.toJson(p);
        return json;
    }
}
