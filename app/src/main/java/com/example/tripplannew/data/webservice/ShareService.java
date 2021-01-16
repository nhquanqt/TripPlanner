package com.example.tripplannew.data.webservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ShareService {
    @GET("/shares")
    Call<Result<List<Share>>> getShareByTripID(@Query("userId") String userId);

    @POST("/share/update")
    Call<Result<Boolean>> updateShare(@Body Share share);

    @POST("/share/delete")
    Call<Result<Boolean>> deleteShareByTripID(@Body Share share);

    @POST("/share/add")
    Call<Result<Boolean>> addShare(@Body Share share);

}
