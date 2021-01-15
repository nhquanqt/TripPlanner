package com.example.tripplannew.data.local;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ExpenseRepository {

    private ExpenseDao mExpenseDao;

    public ExpenseRepository(Application application)
    {
        AppDatabase db = AppDatabase.getDatabase(application);
        mExpenseDao = db.expenseDao();
    }

    public void insert(Expense expense)
    {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mExpenseDao.insert(expense);
        });
    }

    public LiveData<List<Expense>> getAllExpenses(String tripId)
    {
        return mExpenseDao.getAllExpenses(tripId);
    }

    public void deleteExpense(Expense expense)
    {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mExpenseDao.deleteExpense(expense);
        });
    }
}
