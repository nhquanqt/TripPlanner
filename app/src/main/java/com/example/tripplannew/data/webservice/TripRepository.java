package com.example.tripplannew.data.webservice;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripRepository {

    private TripService mTripService;

    public TripRepository()
    {
        mTripService = RetrofitRequest.getRetrofitInstance().create(TripService.class);
    }

    public LiveData<List<Trip>> getTripsByUserId(String userId)
    {
        final MutableLiveData<List<Trip>> data = new MutableLiveData<>();

        mTripService.getTripsByUserId(userId)
                .enqueue(new Callback<Result<List<Trip>>>() {
                    @Override
                    public void onResponse(Call<Result<List<Trip>>> call, Response<Result<List<Trip>>> response) {
                        if(response.body() != null)
                        {
                            data.setValue(response.body().getResult());
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<List<Trip>>> call, Throwable t) {
                        data.setValue(null);
                    }
                });

        return data;
    }

    public LiveData<Boolean> updateTrip(Trip trip)
    {
        MutableLiveData<Boolean> data = new MutableLiveData<>();

        mTripService.updateTripInfo(trip)
                .enqueue(new Callback<Result<Boolean>>() {
                    @Override
                    public void onResponse(Call<Result<Boolean>> call, Response<Result<Boolean>> response) {
                        if (response.body() != null)
                        {
                            data.setValue(response.body().getResult());
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<Boolean>> call, Throwable t) {
                        data.setValue(false);
                    }
                });

        return data;
    }

    public LiveData<Boolean> deleteTrip(Trip trip)
    {
        MutableLiveData<Boolean> data = new MutableLiveData<>();

        mTripService.deleteTrip(trip)
                .enqueue(new Callback<Result<Boolean>>() {
                    @Override
                    public void onResponse(Call<Result<Boolean>> call, Response<Result<Boolean>> response) {
                        if (response.body() != null)
                        {
                            data.setValue(response.body().getResult());
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<Boolean>> call, Throwable t) {
                        Log.i("error", t.getMessage());
                        data.setValue(null);
                    }
                });

        return data;
    }

    public LiveData<Boolean> addTrip(Trip trip)
    {
        MutableLiveData<Boolean> data = new MutableLiveData<>();

        mTripService.addTrip(trip)
                .enqueue(new Callback<Result<Boolean>>() {
                    @Override
                    public void onResponse(Call<Result<Boolean>> call, Response<Result<Boolean>> response) {
                        if (response.body() != null)
                        {
                            data.setValue(response.body().getResult());
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<Boolean>> call, Throwable t) {
                        Log.i("error", t.getMessage());
                        data.setValue(null);
                    }
                });

        return data;
    }
}
