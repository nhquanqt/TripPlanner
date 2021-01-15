package com.example.tripplannew.data.webservice;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {

    private AccountService mAccountService;

    public AccountRepository() {
        mAccountService = RetrofitRequest.getRetrofitInstance().create(AccountService.class);
    }

    public LiveData<Account> login(String username, String password) {
        final MutableLiveData<Account> data = new MutableLiveData<>();

        mAccountService.login(username, password)
                .enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        if(response.body() != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {
                        Log.i("error", t.getMessage());
                        data.setValue(null);
                    }
                });

        return data;
    }

    public LiveData<Boolean> signup(String username, String password, String name) {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();

        Account newAccount = new Account(username, password, name);

        mAccountService.signup(newAccount)
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
                        Log.i("error", t.getMessage().toString());
                    }
                });

        return data;
    }

    public LiveData<Account> getAccountById(String id) {
        final MutableLiveData<Account> data = new MutableLiveData<>();

        mAccountService.getAccountById(id)
                .enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        if(response.body() != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {
                        Log.i("error", t.getMessage());
                        data.setValue(null);
                    }
                });

        return data;
    }

    public LiveData<Boolean> updateProfile(Account account) {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();
        mAccountService.updateProfile(account)
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
                        Log.i("error", t.getMessage().toString());
                    }
                });

        return data;
    }
}
