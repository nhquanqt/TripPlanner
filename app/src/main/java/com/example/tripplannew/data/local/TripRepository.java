package com.example.tripplannew.data.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TripRepository {

    private TripDao mTripDao;

    public TripRepository(Application application)
    {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTripDao = db.tripDao();
    }

    public void insert(Trip trip) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mTripDao.insert(trip);
        });
    }

    public LiveData<List<Trip>> getAllTrips(String userId)
    {
        return mTripDao.getAllTrips(userId);
    }

    public void deleteTrip(Trip trip)
    {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mTripDao.deleteTrip(trip);
        });
    }

    public void updateTrip(Trip trip)
    {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mTripDao.updateTrip(trip);
        });
    }
}
