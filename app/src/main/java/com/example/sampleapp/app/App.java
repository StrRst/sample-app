package com.example.sampleapp.app;

import android.app.Application;

import androidx.room.Room;

import com.example.sampleapp.database.AppDatabase;

public class App extends Application {

    private static App instance;

    private AppDatabase appDatabase;

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return appDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "country_db")
                .allowMainThreadQueries() //TODO: Replace with performing database interactions in separate thread
                .build();
    }
}
