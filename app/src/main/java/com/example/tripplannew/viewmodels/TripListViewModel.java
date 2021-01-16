package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripplannew.data.webservice.Trip;
import com.example.tripplannew.data.webservice.TripRepository;

import java.util.List;

public class TripListViewModel extends AndroidViewModel {

    private com.example.tripplannew.data.local.TripRepository mRepository;
    private TripRepository mWebRepository;
    private String mUserId;

    public TripListViewModel(Application application)
    {
        super(application);
        mRepository = new com.example.tripplannew.data.local.TripRepository(application);
        mWebRepository = new TripRepository();
    }

    public LiveData<String> addTrip(Trip trip)
    {
        return mWebRepository.addTrip(trip);
    }

    public LiveData<List<Trip>> getAllTrips()
    {
        return mWebRepository.getTripsByUserId(mUserId);
    }

    public LiveData<Boolean> deleteTrip(Trip trip)
    {
        return mWebRepository.deleteTrip(trip);
    }
    public LiveData<Boolean> updateTrip(Trip trip)
    {
        return mWebRepository.updateTrip(trip);
    }

    public void setUserId(String userId)
    {
//        Log.i("check", userId);
        mUserId = userId;
    }

    public String getUserId()
    {
        return mUserId;
    }


}
