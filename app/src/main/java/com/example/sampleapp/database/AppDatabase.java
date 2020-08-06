package com.example.sampleapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sampleapp.model.CountryItem;

@Database(entities = {CountryItem.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountryItemDao countryItemDao();
}
