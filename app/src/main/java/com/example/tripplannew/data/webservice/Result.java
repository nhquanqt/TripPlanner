package com.example.tripplannew.data.webservice;

import com.squareup.moshi.Json;

public class Result<T> {

    @Json(name = "result")
    private T mResult;

    public T getResult() {
        return mResult;
    }
}
