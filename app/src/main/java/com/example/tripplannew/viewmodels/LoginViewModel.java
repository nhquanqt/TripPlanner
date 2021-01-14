package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripplannew.data.Account;
import com.example.tripplannew.data.AccountRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private AccountRepository mRepository;

    public LoginViewModel(Application application)
    {
        super(application);
        mRepository = new AccountRepository(application);
    }

    public void insertAccount(Account account)
    {
        mRepository.insert(account);
    }

    public LiveData<List<Account>> getAccount(final String username, final String password)
    {
        return mRepository.getAccount(username, password);
    }

    public LiveData<Integer> countAccounts(final String username)
    {
        return mRepository.countAccounts(username);
    }
}
