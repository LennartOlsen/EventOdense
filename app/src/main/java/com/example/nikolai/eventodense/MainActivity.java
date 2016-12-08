package com.example.nikolai.eventodense;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nikolai.eventodense.components.EventAdapter;
import com.example.nikolai.eventodense.models.Event.Event;
import com.example.nikolai.eventodense.models.Event.EventHttpRepository;
import com.example.nikolai.eventodense.services.LocationService;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Event> events = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        handleEvents();

        if(handleLocationPermissions() && handlePhoneStatePermission()){
            startLocationService();
        }
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

    protected boolean handlePhoneStatePermission(){
        boolean consent = false;

        if (ContextCompat.checkSelfPermission( this, Manifest.permission.READ_PHONE_STATE ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this,
                    new String[] {
                            android.Manifest.permission.READ_PHONE_STATE
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
        if(allowed.contains(Manifest.permission.ACCESS_COARSE_LOCATION) && allowed.contains(Manifest.permission.ACCESS_FINE_LOCATION) && allowed.contains(Manifest.permission.READ_PHONE_STATE)){
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
     * TODO : Handle eventIds properly, Consider first starting this activty when a "TILE" is selected
     */
    private void startLocationService(){
        LocationService.startLocationService(this, "EventOdense");
    }

    private void handleEvents(){
        Log.e("SUCCES", "Call on meeeeeee");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        eventAdapter = new EventAdapter(events);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eventAdapter);

        /**
         * Events API
         */
        EventHttpRepository eventApi = new EventHttpRepository();
        eventApi.get(new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                Log.e("SUCCESS", "Getting the events");
                ArrayList<Event> tempEvents = response.body();

                for (Event tempEvent:
                     tempEvents) {
                    events.add(tempEvent);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                Log.e("ERROR", t.toString());
            }
        });

    }
}
