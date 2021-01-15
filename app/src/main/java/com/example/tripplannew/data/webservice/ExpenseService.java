package com.example.tripplannew.data.webservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ExpenseService {

    @GET("/expenses")
    Call<Result<List<Expense>>> getExpensesByTripId(@Query("idTrip") String userId);

    @POST("/expense/update")
    Call<Result<Boolean>> updateExpenseInfo(@Body Expense expense);

    @POST("/expense/delete")
    Call<Result<Boolean>> deleteExpense(@Body Expense expense);

    @POST("/expense/add")
    Call<Result<Boolean>> addExpense(@Body Expense expense);

}
