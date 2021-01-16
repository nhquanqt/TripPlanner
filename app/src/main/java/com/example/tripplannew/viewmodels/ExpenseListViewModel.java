package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripplannew.data.webservice.Expense;
import com.example.tripplannew.data.local.ExpenseRepository;
import com.example.tripplannew.data.webservice.Trip;

import java.util.List;

public class ExpenseListViewModel extends AndroidViewModel {

    private ExpenseRepository mRepository;
    private com.example.tripplannew.data.webservice.ExpenseRepository mWebRepository;
    private String mTripId;
    private float mTripBudget;
    private Trip mTrip;

    public ExpenseListViewModel(Application application)
    {
        super(application);
        mRepository = new ExpenseRepository(application);
        mWebRepository = new com.example.tripplannew.data.webservice.ExpenseRepository();
    }

//    public void insert(Expense expense)
//    {
////        mRepository.insert(expense);
//    }

    public void addExpense(Expense expense)
    {
        mWebRepository.addExpense(expense);
    }

    public void updateExpense(Expense expense)
    {
        mWebRepository.updateExpense(expense);
    }

    public LiveData<List<Expense>> getAllExpenses()
    {
        return mWebRepository.getExpensesByTripId(mTripId);
//        return mRepository.getAllExpenses(mTripId);
    }

    public LiveData<Boolean> deleteExpense(Expense expense)
    {
        return mWebRepository.deleteExpense(expense);
//        mRepository.deleteExpense(expense);
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

    public float getTripBudget() {
        return mTripBudget;
    }

    public void setTripBudget(float mTripBudget) {
        this.mTripBudget = mTripBudget;
    }
}
