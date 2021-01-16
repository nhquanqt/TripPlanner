package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripplannew.data.webservice.GeoLocationRepository;
import com.example.tripplannew.data.webservice.Place;

import java.util.List;

public class PlaceOfInterestViewModel extends AndroidViewModel {

    private GeoLocationRepository mGeoLocationRepository;

    private String mPlaceType = "atm";

    public PlaceOfInterestViewModel(Application application)
    {
        super(application);
        mGeoLocationRepository = new GeoLocationRepository();
    }

    public void setPlaceType(String placeType)
    {
        mPlaceType = placeType;
    }

    public String getPlaceType()
    {
        return mPlaceType;
    }

    public LiveData<List<Place>> getNearbyPlaces(double lat, double lon, String tag, int radius)
    {
        return mGeoLocationRepository.getNearbyPlaces(lat, lon, tag, radius);
    }

}
