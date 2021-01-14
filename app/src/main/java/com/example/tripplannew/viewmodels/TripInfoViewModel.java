package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tripplannew.data.Trip;

public class TripInfoViewModel extends AndroidViewModel {

    private MutableLiveData<Trip> mTrip;

    public TripInfoViewModel(Application application)
    {
        super(application);
        mTrip = new MutableLiveData<>();
    }

    public void setTrip(Trip trip)
    {
        mTrip.setValue(trip);
    }

    public MutableLiveData<Trip> getTrip()
    {
        return mTrip;
    }
}
