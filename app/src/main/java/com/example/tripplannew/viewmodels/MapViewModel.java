package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tripplannew.data.webservice.GeoLocationRepository;
import com.example.tripplannew.data.webservice.Place;

import java.util.List;

public class MapViewModel extends AndroidViewModel {

    MutableLiveData<String> mLocation;
    int mBackActionId;

    GeoLocationRepository mGeoLocationRepository;

    public MapViewModel(Application application)
    {
        super(application);
        mLocation = new MutableLiveData<>();
        mGeoLocationRepository = new GeoLocationRepository();
    }

    public void setLocation(String location)
    {
        mLocation.setValue(location);
    }

    public MutableLiveData<String> getLocation()
    {
        return mLocation;
    }

    public void setBackActionId(int id)
    {
        mBackActionId = id;
    }

    public int getBackActionId()
    {
        return mBackActionId;
    }

    public LiveData<List<Place>> getNearbyPlaces(double lat, double lon, String tag, int radius)
    {
        return mGeoLocationRepository.getNearbyPlaces(lat, lon, tag, radius);
    }
}
