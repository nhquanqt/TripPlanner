package com.example.tripplannew.data.webservice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpenseRepository {

    private ExpenseService mExpenseService;

    public ExpenseRepository()
    {
        mExpenseService = RetrofitRequest.getRetrofitInstance().create(ExpenseService.class);
    }

    public LiveData<List<Expense>> getExpensesByTripId(String tripId)
    {
        final MutableLiveData<List<Expense>> data = new MutableLiveData<>();

        mExpenseService.getExpensesByTripId(tripId)
                .enqueue(new Callback<Result<List<Expense>>>() {
                    @Override
                    public void onResponse(Call<Result<List<Expense>>> call, Response<Result<List<Expense>>> response) {
                        if(response.body() != null)
                        {
                            data.setValue(response.body().getResult());
                        }
                    }

                    @Override
                    public void onFailure(Call<Result<List<Expense>>> call, Throwable t) {
                        data.setValue(null);
                    }
                });

        return data;
    }

    public LiveData<Boolean> deleteExpense(Expense expense)
    {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();

        mExpenseService.deleteExpense(expense)
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

    public LiveData<Boolean> addExpense(Expense expense)
    {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();

        mExpenseService.addExpense(expense)
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
    public LiveData<Boolean> updateExpense(Expense expense)
    {
        final MutableLiveData<Boolean> data = new MutableLiveData<>();

        mExpenseService.updateExpenseInfo(expense)
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
