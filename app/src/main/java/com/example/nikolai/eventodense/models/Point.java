package com.example.nikolai.eventodense.models;

/**
 * Created by lennartolsen on 17/10/2016.
 */

/**
 * Supposed to be expandable in time
 */
public class Point extends DataModel {

    protected double lat;
    protected double lng;
    protected int timestamp;
    protected float accuracy;

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    private static final String TYPE = "point";

    public Point(double lat, double lng, int timestamp, float accuracy) {
        super();
        this.lat = lat;
        this.lng = lng;
        this.timestamp = timestamp;
        this.accuracy = accuracy;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
