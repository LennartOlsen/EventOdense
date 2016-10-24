package com.example.nikolai.eventodense.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class HttpService extends IntentService {
    private final String TAG = "HttpService";

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String HTTP_SERVICE_GET = "com.example.nikolai.eventodense.services.action.HTTP_SERVICE_GET";
    private static final String ACTION_BAZ = "com.example.nikolai.eventodense.services.action.BAZ";

    // TODO: Rename parameters
    private static final String URL = "com.example.nikolai.eventodense.services.extra.PARAM1";

    public HttpService() {
        super("HttpService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionGet(Context context, String url) {
        Intent intent = new Intent(context, HttpService.class);
        intent.setAction(HTTP_SERVICE_GET);
        intent.putExtra(URL, url);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (HTTP_SERVICE_GET.equals(action)) {
                final String param1 = intent.getStringExtra(URL);
                handleActionGet(param1);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionGet(String url) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            try {
                String first500 = downloadUrl(url);
                Log.e(TAG, first500);
            } catch (IOException e) {
                Log.e(TAG, "Download url went to shit");
            }
        } else {
            // display error
        }
    }


    /** DONT MIND ME **/
    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            java.net.URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.e(TAG, "The response code is: " + response);
            is = conn.getInputStream();

            // Makes sure that the InputStream is closed after the app is
            // finished using it.

            String contentAsString = readIt(is, len);
            return contentAsString;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
