package com.example.nikolai.eventodense.models.Point;

/**
 * Created by lennartolsen on 17/10/2016.
 */

import com.example.nikolai.eventodense.models.IDataModel;

import java.util.UUID;

/**
 * Supposed to be expandable in time
 */
public class Point implements IDataModel {

    private static final String TYPE = "point";

    protected String id;
    protected double lat;
    protected double lng;
    protected int timestamp;
    protected float accuracy;
    protected float altitude;
    protected String eventId;
    protected String deviceId;

    public Point(){}

    public Point(String id, double lat, double lng, int timestamp, float accuracy, float altitude, String eventId, String deviceId) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.timestamp = timestamp;
        this.accuracy = accuracy;
        this.altitude = altitude;
        this.eventId = eventId;
        this.deviceId = deviceId;
    }

    public String getId() {
        if(this.id ==  null || this.id.equals("") ){
            this.id = UUID.randomUUID().toString();
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
