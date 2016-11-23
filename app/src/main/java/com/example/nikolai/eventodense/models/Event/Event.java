package com.example.nikolai.eventodense.models.Event;

/**
 * Created by lennartolsen on 17/10/2016.
 */

import com.example.nikolai.eventodense.models.IDataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Supposed to be expandable in time
 */
public class Event implements IDataModel {

    private static final String TYPE = "event";

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("lat")
    @Expose
    private double lat;

    @SerializedName("lng")
    @Expose
    private double lng;

    @SerializedName("timestamp")
    @Expose
    private int timestamp;

    @SerializedName("accuracy")
    @Expose
    private float accuracy;

    @SerializedName("altitude")
    @Expose
    private float altitude;

    @SerializedName("eventId")
    @Expose
    private String eventId;

    @SerializedName("deviceId")
    @Expose
    private String deviceId;

    public Event(){}

    public Event(String id, double lat, double lng, int timestamp, float accuracy, float altitude, String eventId, String deviceId) {
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
