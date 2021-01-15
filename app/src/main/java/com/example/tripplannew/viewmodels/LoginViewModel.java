package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripplannew.data.local.Account;
import com.example.tripplannew.data.local.AccountRepository;
import com.example.tripplannew.data.webservice.Result;

public class LoginViewModel extends AndroidViewModel {

    private AccountRepository mRepository;
    private com.example.tripplannew.data.webservice.AccountRepository mWebRepository;

    public LoginViewModel(Application application)
    {
        super(application);
        mRepository = new AccountRepository(application);
        mWebRepository = new com.example.tripplannew.data.webservice.AccountRepository();
    }

    public LiveData<Boolean> signup(String username, String password, String name)
    {
        return mWebRepository.signup(username, password, name);
    }

    public LiveData<com.example.tripplannew.data.webservice.Account> login(final String username, final String password)
    {
        return mWebRepository.login(username, password);
//        return mRepository.getAccount(username, password);
    }

    public LiveData<Integer> countAccounts(final String username)
    {
        return mRepository.countAccounts(username);
    }

}
