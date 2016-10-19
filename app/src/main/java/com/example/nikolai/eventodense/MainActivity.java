package com.example.nikolai.eventodense;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nikolai.eventodense.models.Collection;
import com.example.nikolai.eventodense.models.Point;
import com.example.nikolai.eventodense.services.HttpService;
import com.example.nikolai.eventodense.services.LocationService;
import com.example.nikolai.eventodense.utils.DatabaseHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //db = new DatabaseHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if(handleLocationPermissions()){
            startLocationService();
        }

        //HttpService.startActionFoo(this, "http://google.com");
        Collection coll = new Collection();
        Point p = new Point(55.2, 10, 1822058, 20);
        coll.addData(p);
        coll.setId("123546");
        coll.setEventID("88080");
        System.out.println(coll.toJson());
    }

    /**
     * Makes sure that all of our permission requests have been handled
     * LOCATION HANDLERS ARE FOR API-24 AND UP
     */
    protected boolean handleLocationPermissions(){
        boolean consent = false;

        if (ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this,
                    new String[] {
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    1);
            consent = false;
        } else {
            consent = true;
        }

        return consent;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        ArrayList<String> allowed = new ArrayList<String>();
        for (int i = 0; i < permissions.length; i++) {
            if(grantResults[i] <= 0){
                allowed.add(permissions[i]);
            }
        }
        //Call out location service starter if all is good thank fuck for api-24 n' up
        if(allowed.contains(Manifest.permission.ACCESS_COARSE_LOCATION) && allowed.contains(Manifest.permission.ACCESS_FINE_LOCATION)){
            startLocationService();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * TODO : Handle eventIds properly
     */
    private void startLocationService(){
        LocationService.startLocationService(this, "EventOdense");
    }
}
