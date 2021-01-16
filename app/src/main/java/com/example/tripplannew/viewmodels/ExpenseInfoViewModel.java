package com.example.tripplannew.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tripplannew.data.webservice.Expense;

public class ExpenseInfoViewModel extends AndroidViewModel {

    private MutableLiveData<Expense> mExpense;

    public ExpenseInfoViewModel(Application application)
    {
        super(application);
        mExpense = new MutableLiveData<>();
    }

    public void setExpense(Expense expense)
    {
        mExpense.setValue(expense);
    }

    public MutableLiveData<Expense> getExpense()
    {
        return mExpense;
    }
}
