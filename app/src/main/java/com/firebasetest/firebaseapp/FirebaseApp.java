package com.firebasetest.firebaseapp;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by sarath on 20/10/16.
 */

public class FirebaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
