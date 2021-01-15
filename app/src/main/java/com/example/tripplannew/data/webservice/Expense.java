package com.example.tripplannew.data.webservice;

import com.squareup.moshi.Json;

public class Expense {

    static final public int TYPE_FOOD = 1;
    static final public int TYPE_DRINK = 2;

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

    public Expense(String tripId, String expenseName, float cost, int type)
    {
        this.tripId = tripId;
        this.expenseName = expenseName;
        this.cost = cost;
//        this.mBitMapID = bitMapID;
        this.type = type;
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
}
