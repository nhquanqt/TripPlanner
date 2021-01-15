package com.example.tripplannew.data.webservice;

import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitRequest {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            Moshi moshi = new Moshi.Builder().build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.17:8080")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build();
        }

        return retrofit;
    }
}
