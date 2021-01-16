package com.example.tripplannew.data.webservice;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeoLocationRepository {

    private GeoLocationService mGeoLocationService;

    final private String API_KEY = "pk.941354f3571d4b2dd02adcc405f1c730";
    final private String[] placeTypes = {"restaurant", "hotel", "cinema", "place_of_worship", "park"};

    public GeoLocationRepository()
    {
        mGeoLocationService = RetrofitRequest.getRetrofitInstance().create(GeoLocationService.class);
    }

    public LiveData<List<Place>> getNearbyPlaces(double lat, double lon, String tag, int radius)
    {
        final MutableLiveData<List<Place>> data = new MutableLiveData<>();

        mGeoLocationService.getNearbyPlaces(API_KEY, lat, lon, tag, radius)
                .enqueue(new Callback<List<Place>>() {
                    @Override
                    public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                        if(response.body() != null)
                        {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Place>> call, Throwable t) {
                        Log.i("error", t.getMessage());
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<List<Place>> getNearbyPlaces(double lat, double lon, int radius)
    {
        final MutableLiveData<List<Place>> data = new MutableLiveData<>();

        ArrayList<Place> placeList = new ArrayList<>();

        for(int i = 0; i < placeTypes.length; ++i)
        {
            mGeoLocationService.getNearbyPlaces(API_KEY, lat, lon, placeTypes[i], radius)
                    .enqueue(new Callback<List<Place>>() {
                        @Override
                        public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                            if(response.body() != null)
                            {
                                placeList.addAll(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Place>> call, Throwable t) {

                        }
                    });
        }

        data.setValue(placeList);

        return data;
    }

    public LiveData<Place> getPlaceInfo(double lat, double lon)
    {
        final MutableLiveData<Place> data = new MutableLiveData<>();

        mGeoLocationService.getPlaceInfo(API_KEY, lat, lon)
                .enqueue(new Callback<Place>() {
                    @Override
                    public void onResponse(Call<Place> call, Response<Place> response) {
                        if(response.body() != null)
                        {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Place> call, Throwable t) {
                        data.setValue(null);
                    }
                });

        return data;
    }
}
