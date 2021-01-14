package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MapViewModel extends AndroidViewModel {

    MutableLiveData<String> mLocation;
    int mBackActionId;

    public MapViewModel(Application application)
    {
        super(application);
        mLocation = new MutableLiveData<>();
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
}
