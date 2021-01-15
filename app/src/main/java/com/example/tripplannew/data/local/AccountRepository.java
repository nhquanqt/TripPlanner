package com.example.tripplannew.data.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountRepository {

    private AccountDao mAccountDao;

    public AccountRepository(Application application)
    {
        AppDatabase db = AppDatabase.getDatabase(application);
        mAccountDao = db.accountDao();
    }

    public void insert(final Account account)
    {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mAccountDao.insert(account);
        });
    }

    public void update(Account account)
    {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mAccountDao.update(account);
        });
    }

    public LiveData<List<Account>> getAccount(final String username, final String password)
    {
        return mAccountDao.getAccount(username, password);
    }

    public LiveData<Integer> countAccounts(final String username)
    {
        return mAccountDao.countAccounts(username);
    }

    public LiveData<Account> getAccount(String userId)
    {
        return mAccountDao.getAccount(userId);
    }
}
