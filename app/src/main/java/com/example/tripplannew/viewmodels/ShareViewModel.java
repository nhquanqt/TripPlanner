package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripplannew.data.webservice.Share;
import com.example.tripplannew.data.webservice.ShareRepository;
import com.example.tripplannew.data.webservice.Trip;

import java.util.List;

public class ShareViewModel extends AndroidViewModel {
    private ShareRepository mWebRepository;
    private String mTripId;

    public ShareViewModel(@NonNull Application application) {
        super(application);
        mWebRepository = new ShareRepository();
    }
    public LiveData<Boolean> addShare(Share share)
    {
        return mWebRepository.addShare(share);
    }

    public LiveData<List<Share>> getShareByTripID()
    {
        return mWebRepository.getShareByTripID(mTripId);
    }

    public LiveData<Boolean> deleteShareByTripID(Share share)
    {
        return mWebRepository.deleteShareByTripID(share);
    }
    public LiveData<Boolean> updateShare(Share share)
    {
        return mWebRepository.updateShare(share);
    }

    public void setTripId(String mTripId) {
        this.mTripId = mTripId;
    }
    public String getTripId() {
        return mTripId;
    }
}
