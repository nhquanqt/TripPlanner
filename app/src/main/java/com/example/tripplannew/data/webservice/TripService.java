package com.example.tripplannew.data.webservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TripService {

    @GET("/trips")
    Call<Result<List<Trip>>> getTripsByUserId(@Query("userId") String userId);

    @POST("/trip/update")
    Call<Result<Boolean>> updateTripInfo(@Body Trip trip);

    @POST("/trip/delete")
    Call<Result<Boolean>> deleteTrip(@Body Trip trip);

    @POST("/trip/add")
    Call<Result<String>> addTrip(@Body Trip trip);
}
