package com.example.nikolai.eventodense;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * helper methods.
 */
public class LocationPullService extends IntentService {

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_PULL_LOCATION = "com.example.nikolai.eventodense.action.PullLocation";

    public LocationPullService() {
        super("LocationPullService");
    }

    /**
     * Helper method
     * Starts the ActionPullLocation intent on the context provided
     *
     * @see IntentService
     */
    public static void startActionPullLocation(Context context) {
        Intent intent = new Intent(context, LocationPullService.class);
        intent.setAction(ACTION_PULL_LOCATION);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            handleActionPullLocation();
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionPullLocation() {
        Log.e("INFO", "Hello FROM PULL LOCATION ACTION");

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                Log.e("INFO", location.toString());
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.e("INFO", "Status Changed");
            }

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };
        try {
            //
            if (locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)) {
                Log.e("INFO", "using NETWORK_PROVIDER");
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }

            if (locationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER)) {
                Log.e("INFO", "using GPS_PROVIDER");
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }

            Log.e("INFO", "TRYING REQUEST LOCATION");
        } catch (SecurityException e){
            Log.e("ERROR", "ALLOW ME PLEASE");
        }
    }
}
