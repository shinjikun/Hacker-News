package com.ycombinator.news.hackernews;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by leonardoilagan on 26/06/2016.
 */

public class HackerNewsApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize the firebase within the application
        Firebase.setAndroidContext(this);
    }
}
