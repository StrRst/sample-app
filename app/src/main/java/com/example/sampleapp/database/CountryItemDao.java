package com.example.sampleapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sampleapp.model.CountryItem;

import java.util.List;

@Dao
public interface CountryItemDao {

    @Query("SELECT * FROM countries")
    List<CountryItem> getAll();

    @Query("SELECT * FROM countries WHERE id = :id")
    CountryItem getById(int id);

    @Insert
    void insert(CountryItem item);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<CountryItem> items);

    @Update
    void update(CountryItem item);

    @Delete
    void delete(CountryItem item);

    @Query("DELETE FROM countries")
    void deleteAll();
}
