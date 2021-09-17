package com.example.library;

import android.app.Application;

import com.example.library.lib.api.NpsInfo;

public class Library extends Application {
    public static final String BASE_URL= "http://apiuat.yoapp.com.np/api/";

    private static final String USERNAME = "wePayApiUser";
    private static final String PASSWORD = "wePayAp1us3r@20 ";
    private static final String PRIVATE_KEY = "73FF9B25E1064EBE884611EB038D997B-81F971764D1E451ABCE25C281A1E5810==";

    @Override
    public void onCreate() {
        super.onCreate();
        new NpsInfo
                .Builder(getApplicationContext())
                .setUrl(BASE_URL)
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .setPrivateKey(PRIVATE_KEY)
                .build()
                .apply();
    }
}