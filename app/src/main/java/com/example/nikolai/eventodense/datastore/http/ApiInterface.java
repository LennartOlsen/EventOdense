package com.example.nikolai.eventodense.datastore.http;

import com.example.nikolai.eventodense.models.IDataModel;
import com.example.nikolai.eventodense.models.Point.Point;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lennartolsen on 06/11/2016.
 */

/**
 * TODO: MOVE INTERFACE TO POINTS, MAYBE INCLUDE WITHIN HTTP REPOS
 * If moved to points this interface should only hold call specifications that are specific to the point model
 */
public interface ApiInterface {
    @POST("points")
    Call<ArrayList<Point>> createPoints(@Body ArrayList<Point> points);
}
