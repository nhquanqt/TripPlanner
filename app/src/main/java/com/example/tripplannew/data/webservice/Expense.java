package com.example.tripplannew.data.webservice;

import com.squareup.moshi.Json;

public class Expense {

    static final public int TYPE_FOOD = 0;
    static final public int TYPE_DRINK = 1;
    static final public int TYPE_MOVE = 2;
    static final public int TYPE_RENT = 3;
    static final public int TYPE_ENTERTAINMENT = 4;
    static final public int TYPE_PHONE = 5;
    static final public int TYPE_OTHER = 6;

    @Json(name = "id")
    private String id;
    @Json(name = "idTrip")
    private String tripId;
    @Json(name = "expenseName")
    private String expenseName;
    @Json(name = "cost")
    private float cost;
    @Json(name = "type")
    private int type;
    @Json(name = "date")
    private String date;
    @Json(name = "place")
    private String place;

    public Expense(String tripId, String expenseName, float cost, int type, String date, String place)
    {
        this.tripId = tripId;
        this.expenseName = expenseName;
        this.cost = cost;
        this.type = type;
        this.date = date;
        this.place = place;
    }

    public void update(String expenseName, float cost, int type, String date, String place)
    {
        this.expenseName = expenseName;
        this.cost = cost;
        this.type = type;
        this.date = date;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
