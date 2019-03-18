package com.example.jmarkovic16_homework1;


import timber.log.Timber;

public class ApplicationStarter extends android.app.Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
