package com.example.tripplannew.data.webservice;

import com.squareup.moshi.Json;

public class Account {

    @Json(name = "id")
    private String mId;

    @Json(name = "username")
    private String mUsername;

    @Json(name = "password")
    private String mPassword;

    @Json(name = "name")
    private String mName;

    @Json(name = "dateOfBirth")
    private String mDateOfBirth;

    @Json(name = "placeOfBirth")
    private String mPlaceOfBirth;

    public Account(String id, String username, String password, String name, String dateOfBirth, String placeOfBirth)
    {
        mId = id;
        mUsername = username;
        mPassword = password;
        mName = name;
        mDateOfBirth = dateOfBirth;
        mPlaceOfBirth = placeOfBirth;
    }

    public Account(String username, String password, String name)
    {
        mUsername = username;
        mPassword = password;
        mName = name;
    }

    public Account(String id, String name, String dateOfBirth, String placeOfBirth)
    {
        mId = id;
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
