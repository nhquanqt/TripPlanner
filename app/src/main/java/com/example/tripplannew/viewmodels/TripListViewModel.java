package com.example.tripplannew.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripplannew.data.Trip;
import com.example.tripplannew.data.TripRepository;

import java.util.List;

public class TripListViewModel extends AndroidViewModel {

    private TripRepository mRepository;
    private String mUserId;

    public TripListViewModel(Application application)
    {
        super(application);
        mRepository = new TripRepository(application);
    }

    public void insert(Trip trip)
    {
        mRepository.insert(trip);
    }

    public LiveData<List<Trip>> getAllTrips()
    {
        return mRepository.getAllTrips(mUserId);
    }

    public void deleteTrip(Trip trip)
    {
        mRepository.deleteTrip(trip);
    }
    public void updateTrip(Trip trip)
    {
        mRepository.updateTrip(trip);
    }

    public void setUserId(String userId)
    {
        mUserId = userId;
    }

    public String getUserId()
    {
        return mUserId;
    }


}
