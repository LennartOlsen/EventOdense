package com.example.nikolai.eventodense.models;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lennartolsen on 17/10/2016.
 */

public class Collection extends DataModel {
    private static final String TYPE = "collection";

    public List<Point> data;
    public String id;

    public String eventID;


    public Collection(List<Point> data, String id, String eventID) {
        super();
        this.data = data;
        this.id = id;
        this.eventID = eventID;
    }

    public Collection(){
        super();
    }

    public List<Point> getData() {
        return data;
    }

    public void setData(List<Point> data) {
        this.data = data;
    }

    public void addData(Point data){
        if(this.data == null){
            this.data = new ArrayList<Point>();
        }
        this.data.add(data);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
}
