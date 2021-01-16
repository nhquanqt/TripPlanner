package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripplannew.data.webservice.GeoLocationRepository;

public class PlaceOfInterestViewModel extends AndroidViewModel {

    private GeoLocationRepository mGeoLocationRepository;

    public PlaceOfInterestViewModel(Application application)
    {
        super(application);
        mGeoLocationRepository = new GeoLocationRepository();
    }
}
