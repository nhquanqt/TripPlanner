package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tripplannew.data.webservice.Expense;
import com.example.tripplannew.data.local.ExpenseRepository;
import com.example.tripplannew.data.webservice.Trip;

import java.util.ArrayList;
import java.util.List;

public class ExpenseListViewModel extends AndroidViewModel {

    private ExpenseRepository mRepository;
    private com.example.tripplannew.data.webservice.ExpenseRepository mWebRepository;
    private String mTripId;
    private float mTripBudget;
    private Trip mTrip;
    private float mTotalCost;
    private ArrayList<Expense> mExpenses;

    public ExpenseListViewModel(Application application)
    {
        super(application);
        mRepository = new ExpenseRepository(application);
        mWebRepository = new com.example.tripplannew.data.webservice.ExpenseRepository();
        mTotalCost = 0;
    }

//    public void insert(Expense expense)
//    {
////        mRepository.insert(expense);
//    }

    public void setTotalCost(float totalCost)
    {
        mTotalCost = totalCost;
    }

    public float getTotalCost()
    {
        return mTotalCost;
    }

    public LiveData<Boolean> addExpense(Expense expense)
    {
        return mWebRepository.addExpense(expense);
    }

    public LiveData<Boolean> updateExpense(Expense expense)
    {
        return mWebRepository.updateExpense(expense);
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

    public ArrayList<Expense> getExpenses() {
        return mExpenses;
    }

    public void setExpenses(ArrayList<Expense> expenses) {
        this.mExpenses = expenses;
    }
}
