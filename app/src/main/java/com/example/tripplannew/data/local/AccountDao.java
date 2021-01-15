package com.example.tripplannew.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Account account);

    @Query("DELETE FROM account_table")
    void deleteAll();

    @Query("SELECT * FROM account_table WHERE username = :username and password = :password")
    LiveData<List<Account>> getAccount(String username, String password);

    @Query("SELECT COUNT(*) FROM account_table WHERE username = :username")
    LiveData<Integer> countAccounts(String username);

    @Query("SELECT * FROM account_table WHERE id = :id")
    LiveData<Account> getAccount(String id);

    @Update
    void update(Account account);
}
