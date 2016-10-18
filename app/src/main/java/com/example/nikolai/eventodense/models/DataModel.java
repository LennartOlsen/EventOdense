package com.example.nikolai.eventodense.models;

import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lennartolsen on 17/10/2016.
 */

public class DataModel {

    // GSON needs to be ignored in order to work (i think it tries to serialize it self)
    private transient Gson gson;

    DataModel(){
        this.gson = new Gson();
    }

    public String toJson() {
        return gson.toJson(this);
    }
}
