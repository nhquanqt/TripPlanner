package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripplannew.data.Account;
import com.example.tripplannew.data.AccountRepository;

import java.util.List;

public class SettingViewModel extends AndroidViewModel {

    private AccountRepository mRepository;

    public SettingViewModel(Application application)
    {
        super(application);
        mRepository = new AccountRepository(application);
    }

    public LiveData<Account> getAccount(String userId)
    {
        return mRepository.getAccount(userId);
    }
    public void updateAccount(Account account)
    {
        mRepository.update(account);
    }
}
