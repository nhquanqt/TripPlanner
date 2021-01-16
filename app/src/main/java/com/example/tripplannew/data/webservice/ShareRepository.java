package com.example.tripplannew.data.webservice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareRepository {
    private ShareService mShareService;

    public ShareRepository (){
        mShareService = RetrofitRequest.getRetrofitInstance().create(ShareService.class);
    }

    public LiveData<List<Share>> getShareByTripID(String userId){
        final MutableLiveData<List<Share>> data = new MutableLiveData<>();
        mShareService.getShareByTripID(userId)
                .enqueue(new Callback<Result<List<Share>>>() {
                    @Override
                    public void onResponse(Call<Result<List<Share>>> call, Response<Result<List<Share>>> response) {
                        if(response.body() != null)
                        {
                            data.setValue(response.body().getResult());
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<List<Share>>> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
    public LiveData<Boolean> deleteShareByTripID(Share share)
    {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();

        mShareService.deleteShareByTripID(share)
                .enqueue(new Callback<Result<Boolean>>() {
                    @Override
                    public void onResponse(Call<Result<Boolean>> call, Response<Result<Boolean>> response) {
                        if(response.body() != null)
                        {
                            data.setValue(response.body().getResult());
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<Boolean>> call, Throwable t) {
                        data.setValue(null);
                    }
                });

        return data;
    }
    public LiveData<Boolean> addShare(Share share)
    {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();

        mShareService.addShare(share)
                .enqueue(new Callback<Result<Boolean>>() {
                    @Override
                    public void onResponse(Call<Result<Boolean>> call, Response<Result<Boolean>> response) {
                        if(response.body() != null)
                        {
                            data.setValue(response.body().getResult());
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<Boolean>> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
    public LiveData<Boolean> updateShare(Share share)
    {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();

        mShareService.updateShare(share)
                .enqueue(new Callback<Result<Boolean>>() {
                    @Override
                    public void onResponse(Call<Result<Boolean>> call, Response<Result<Boolean>> response) {
                        if(response.body() != null)
                        {
                            data.setValue(response.body().getResult());
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<Boolean>> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

}
