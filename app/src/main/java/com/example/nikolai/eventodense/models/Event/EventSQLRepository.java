package com.example.nikolai.eventodense.models.Event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.nikolai.eventodense.datastore.sql.DatabaseHelper;
import com.example.nikolai.eventodense.datastore.sql.ISqlRepository;
import com.example.nikolai.eventodense.models.IDataModel;

import java.util.ArrayList;

/**
 * Created by lennartolsen on 21/10/2016.
 */

/**
 * TODO : IMPLEMENT AND UPDATE ME!!!!
 */

public class EventSQLRepository extends ISqlRepository<Event> {

    private DatabaseHelper _helper;
    private DatabaseHelper dbh;

    /** Holy smokes not having statics in interfaces sucks **/
    private static final String TABLE_NAME = "locations";
    public String getTableName() {
        return TABLE_NAME;
    }

    private static final int MAX_BATCH_SIZE = 10;
    public int getMaxBatchSize(){ return MAX_BATCH_SIZE; }

    /**
     * DO NOT USE THIS CONSTRUCTOR
     * Used purely for telling the DatabaseHelper my TABLE_NAME when constructing DBS
     * Solutions are welcome
     */
    public EventSQLRepository(){}

    public EventSQLRepository(Context c){
        DatabaseHelper h = dbh.getHelper(c);
        this._helper = h;
    }

    @Override
    public Event get(String id) {
        return FromSQLToModel(
                _helper.getModel(this, id)
        );
    }

    @Override
    public ArrayList<Event> get() {
        Cursor res = _helper.getModel(this);
        ArrayList<Event> points = new ArrayList<Event>();
        while(!res.isAfterLast()){
            points.add(FromSQLToModel(res));
            res.moveToNext();
        }
        return points;
    }

    /**
     * Gets the a finite number of datamodels limited pr timestamp
     * @param limit
     * @return
     */
    @Override
    public ArrayList<Event> get(int limit) {
        Cursor res = _helper.getModel(this, limit);
        ArrayList<Event> events = new ArrayList<Event>();
        while(!res.isAfterLast()){
            events.add(FromSQLToModel(res));
            res.moveToNext();
        }
        return events;
    }

    @Override
    public Boolean save(Event model) {
        ContentValues values = this.FromModelToSQL(model);
        return _helper.insert(this, values);
    }

    @Override
    public int count(){
        Cursor res = _helper.getModel(this);
        return res.getCount();
    }

    /**
     * Hopeless implementation of batch save
     * @param models
     * @return
     */
    @Override
    public Boolean save(ArrayList<Event> models) {
        Boolean rtn = true;
        for (IDataModel p:
             models) {
            ContentValues values = this.FromModelToSQL(p);
            if(!_helper.insert(this, values)){
                rtn = false;
            }
        }
        return rtn;
    }

    public String createDBString(){
        return "CREATE TABLE " + TABLE_NAME + "" +
                "(id TEXT PRIMARY KEY, " +
                "lat REAL, " +
                "lng REAL, " +
                "timestamp INTEGER, " +
                "accuracy REAL, " +
                "altitude REAL, " +
                "eventId String, " +
                "deviceId String)";
    }

    private Event FromSQLToModel(Cursor c){
        Event e = new Event();
        /*p.setId(c.getString(0));
        p.setLat(c.getDouble(1));
        p.setLng(c.getDouble(2));
        p.setTimestamp(c.getInt(3));
        p.setAccuracy(c.getFloat(4));
        p.setAltitude(c.getFloat(5));
        p.setEventId(c.getString(6));
        p.setDeviceId(c.getString(7));*/
        return e;
    }

    private ContentValues FromModelToSQL(IDataModel model) {
        Event e = (Event) model;
        ContentValues cv = new ContentValues();
        /*cv.put("id", p.getId());
        cv.put("lat", p.getLat());
        cv.put("lng", p.getLng());
        cv.put("timestamp", p.getTimestamp());
        cv.put("accuracy", p.getAccuracy());
        cv.put("eventId", p.getEventId());*/
        return cv;
    }
}
