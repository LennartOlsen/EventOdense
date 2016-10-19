package com.example.nikolai.eventodense.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.example.nikolai.eventodense.models.Point;
import com.example.nikolai.eventodense.utils.DeviceUUID;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class LocationHandleIntent extends IntentService {
    private static final String TAG = "LocationHandleIntent";

    private static final String ACTION_HANDLE = "com.example.nikolai.eventodense.services.LocationHandleIntent.action.ACTION_HANDLE";

    private static final String LOCATION = "com.example.nikolai.eventodense.services.LocationHandleIntent.extra.LOCATION";
    private static final String EVENT_ID = "com.example.nikolai.eventodense.services.LocationHandleIntent.extra.EVENT_ID";
    private static final String TIMESTAMP = "com.example.nikolai.eventodense.services.LocationHandleIntent.extra.TIMESTAMP";

    public LocationHandleIntent() {
        super("LocationHandleIntent");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     */
    public static void startActionLocationHandle(Context context, Location location, String event_id, int timestamp) {
        Intent intent = new Intent(context, LocationHandleIntent.class);
        intent.setAction(ACTION_HANDLE);
        intent.putExtra(LOCATION, location);
        intent.putExtra(EVENT_ID, event_id);
        intent.putExtra(TIMESTAMP, timestamp);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_HANDLE.equals(action)) {
                final Location location = intent.getParcelableExtra(LOCATION);
                final String event_id = intent.getStringExtra(EVENT_ID);
                final int timestamp  = intent.getIntExtra(TIMESTAMP, 0);
                handleActionLocationHandle(location, event_id, timestamp);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionLocationHandle(Location location, String event_id, int timestamp) {

        Point p = new Point("",
                location.getLatitude(),
                location.getLongitude(),
                timestamp,
                location.getAccuracy(),
                (float) location.getAltitude(),
                event_id,
                "");

        Log.e(TAG, p.toJson());
    }
}
