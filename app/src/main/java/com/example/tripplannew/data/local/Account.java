package com.example.tripplannew.data.local;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "account_table")
public class Account {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String mId;

    @NonNull
    @ColumnInfo(name = "username")
    private String mUsername;

    @NonNull
    @ColumnInfo(name = "password")
    private String mPassword;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "dateOfBirth")
    private String mDateOfBirth;

    @ColumnInfo(name = "placeOfBirth")
    private String mPlaceOfBirth;

    @Ignore
    public Account(String username, String password, String name)
    {
        mId = UUID.randomUUID().toString();
        mUsername = username;
        mPassword = password;
        mName = name;
    }

    @Ignore
    public Account(String username, String password, String name, String dateOfBirth, String placeOfBirth)
    {
        mId = UUID.randomUUID().toString();
        mUsername = username;
        mPassword = password;
        mName = name;
        mDateOfBirth = dateOfBirth;
        mPlaceOfBirth = placeOfBirth;
    }

    public Account(String id, String username, String password, String name, String dateOfBirth, String placeOfBirth)
    {
        mId = id;
        mUsername = username;
        mPassword = password;
        mName = name;
        mDateOfBirth = dateOfBirth;
        mPlaceOfBirth = placeOfBirth;
    }

    public void update(String name, String dateOfBirth, String placeOfBirth)
    {
        mName = name;
        mDateOfBirth = dateOfBirth;
        mPlaceOfBirth = placeOfBirth;
    }

    public boolean isValid()
    {
        return mUsername.length() > 0;
    }

    public String getId()
    {
        return mId;
    }

    public String getUsername()
    {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getName()
    {
        return mName;
    }

    public String getDateOfBirth() { return mDateOfBirth; }

    public String getPlaceOfBirth() { return mPlaceOfBirth; }
}
