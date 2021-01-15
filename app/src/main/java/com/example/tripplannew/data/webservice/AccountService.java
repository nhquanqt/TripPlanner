package com.example.tripplannew.data.webservice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccountService {

    @GET("/login")
    Call<Account> login(@Query("u") String username, @Query("p") String password);

    @GET("/user")
    Call<Account> getAccountById(@Query("id") String id);

    @POST("/signup")
    Call<Result<Boolean>> signup(@Body Account account);

    @POST("/user/update")
    Call<Result<Boolean>> updateProfile(@Body Account account);
}
