package com.example.nikolai.eventodense.models.Event;

import com.example.nikolai.eventodense.datastore.http.ApiClient;
import com.example.nikolai.eventodense.datastore.http.ApiInterface;
import com.example.nikolai.eventodense.datastore.http.IHttpRepository;
import com.example.nikolai.eventodense.models.IDataModel;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by lennartolsen on 24/10/2016.
 */

public class EventHttpRepository extends IHttpRepository<Event> {

    ApiInterface api;

    public EventHttpRepository(){
        api = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public Event get(String id) {
        return null;
    }

    @Override
    public void get(Callback<ArrayList<Event>> CallBack) {
        Call<ArrayList<Event>> call = api.getEvents();
        call.enqueue(CallBack);
    }

    @Override
    public void save(Event p, Callback<Event[]> cb) {}

    @Override
    public void save(ArrayList<Event> p, Callback<ArrayList<Event>> cb) {}


    public int count() {
        return 0;
    }

    private String FromModelToJson(IDataModel model){
        Gson jsoner = new Gson();
        Event e = (Event) model;
        String json = jsoner.toJson(e);
        return json;
    }

    private String FromArrayListToJson(ArrayList<IDataModel> models){
        Gson jsoner = new Gson();
        String json = jsoner.toJson(models);
        return json;
    }
}
