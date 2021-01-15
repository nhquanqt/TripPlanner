package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripplannew.data.local.AccountRepository;

public class SettingViewModel extends AndroidViewModel {

    private AccountRepository mRepository;
    private com.example.tripplannew.data.webservice.AccountRepository mWebRepository;

    public SettingViewModel(Application application)
    {
        super(application);
        mRepository = new AccountRepository(application);
        mWebRepository = new com.example.tripplannew.data.webservice.AccountRepository();
    }

    public LiveData<com.example.tripplannew.data.webservice.Account> getAccount(String userId)
    {
        return mWebRepository.getAccountById(userId);
    }
    public LiveData<Boolean> updateProfile(com.example.tripplannew.data.webservice.Account account)
    {
        return mWebRepository.updateProfile(account);
    }
}
