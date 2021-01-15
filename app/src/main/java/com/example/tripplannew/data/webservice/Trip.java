package com.example.tripplannew.data.webservice;

import com.squareup.moshi.Json;

public class Trip {

    @Json(name = "idUser")
    private String userId;
    @Json(name = "id")
    private String id;
    @Json(name = "tripName")
    private String tripName;
    @Json(name = "budget")
    private float budget;
    @Json(name = "startDate")
    private String startDate;
    @Json(name = "endDate")
    private String endDate;
    @Json(name = "departure")
    private String departure;

    public Trip(String id, String userId, String tripName, float budget, String startDate, String endDate, String departure)
    {
        this.id = id;
        this.userId = userId;
        this.tripName = tripName;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.departure = departure;
    }

    public Trip(String userId, String tripName, float budget, String startDate, String endDate, String departure)
    {
        this.userId = userId;
        this.tripName = tripName;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.departure = departure;
    }

    public void update(String tripName, float budget, String startDate, String endDate, String departure)
    {
        this.tripName = tripName;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.departure = departure;
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public float getBudget() {
        return budget;
    }
    public void setBudget(float budget) {
        this.budget = budget;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

}
