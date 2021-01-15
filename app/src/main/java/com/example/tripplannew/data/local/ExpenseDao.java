package com.example.tripplannew.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Expense expense);

    @Query("SELECT * FROM expense_table WHERE tripId = :tripId")
    LiveData<List<Expense>> getAllExpenses(String tripId);

    @Delete
    void deleteExpense(Expense expense);
}
