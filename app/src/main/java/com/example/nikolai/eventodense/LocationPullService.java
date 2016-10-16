package com.example.nikolai.eventodense;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * helper methods.
 */
@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class LocationPullService extends IntentService {

    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_PULL_LOCATION = "com.example.nikolai.eventodense.action.PullLocation";

    private static final String LOCATION_PARAM = "com.example.nikolai.eventodense.extra.location";

    public LocationPullService() {
        super("LocationPullService");
    }

    /**
     * Helper method
     * Starts the ActionPullLocation intent on the context provided
     *
     * @see IntentService
     */
    public static void startActionPullLocation(Context context, Location param1) {
        Intent intent = new Intent(context, LocationPullService.class);
        intent.setAction(ACTION_PULL_LOCATION);
        intent.putExtra(LOCATION_PARAM, param1);

        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final Location param1 = intent.getParcelableExtra(LOCATION_PARAM);
            handleActionPullLocation(param1);
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionPullLocation(Location location) {
        Log.e("INFO", "YAYAYAYAYA");
        Log.e("INFO", location.toString());
    }
}
