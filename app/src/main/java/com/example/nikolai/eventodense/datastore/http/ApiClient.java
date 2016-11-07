package com.example.nikolai.eventodense.datastore.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lennartolsen on 06/11/2016.
 */

public class ApiClient {
    /** BASE_URL NEEDS TO BE UPDATED ACCORDINGLY MEMBER TRAILING SLASHES YOYO*/
    public static final String BASE_URL = "http://10.0.2.2:8080/";

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
