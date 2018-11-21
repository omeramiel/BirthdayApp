package com.omeram.birthday;

import android.app.Application;

import timber.log.Timber;

public class BirthdayApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
