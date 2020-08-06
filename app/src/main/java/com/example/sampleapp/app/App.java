package com.example.sampleapp.app;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.sampleapp.database.AppDatabase;

public class App extends Application {

    private static Context context;

    private AppDatabase appDatabase;

    public static Context getContext() {
        return context;
    }

    public AppDatabase getDatabase() {
        return appDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "country_db")
                .allowMainThreadQueries() //TODO: Replace with performing database interactions in separate thread
                .build();
    }
}
