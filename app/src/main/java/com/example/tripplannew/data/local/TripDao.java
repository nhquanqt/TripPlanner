package com.example.tripplannew.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Trip trip);

    @Query("SELECT * FROM trip_table WHERE userId = :userId")
    LiveData<List<Trip>> getAllTrips(String userId);

    @Delete
    void deleteTrip(Trip trip);

    @Update
    void updateTrip(Trip trip);
}
