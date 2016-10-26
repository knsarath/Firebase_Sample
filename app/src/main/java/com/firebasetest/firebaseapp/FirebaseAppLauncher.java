package com.firebasetest.firebaseapp;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sarath on 20/10/16.
 */

public class FirebaseAppLauncher extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * For new version
         */

        if (!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
