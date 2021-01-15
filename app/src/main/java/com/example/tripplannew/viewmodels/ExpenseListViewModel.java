package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripplannew.data.local.Expense;
import com.example.tripplannew.data.local.ExpenseRepository;
import com.example.tripplannew.data.local.Trip;

import java.util.List;

public class ExpenseListViewModel extends AndroidViewModel {

    private ExpenseRepository mRepository;
    private String mTripId;
    private Trip mTrip;

    public ExpenseListViewModel(Application application)
    {
        super(application);
        mRepository = new ExpenseRepository(application);
    }

    public void insert(Expense expense)
    {
        mRepository.insert(expense);
    }

    public LiveData<List<Expense>> getAllExpenses()
    {
        return mRepository.getAllExpenses(mTripId);
    }

    public void deleteExpense(Expense expense)
    {
        mRepository.deleteExpense(expense);
    }

    public void setTripId(String tripId)
    {
        mTripId = tripId;
    }

    public String getTripId()
    {
        return mTripId;
    }

    public void setTrip(Trip trip)
    {
        mTrip = trip;
    }

    public Trip getTrip() { return mTrip; }
}
