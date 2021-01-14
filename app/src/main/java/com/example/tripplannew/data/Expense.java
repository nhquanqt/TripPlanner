package com.example.tripplannew.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(
        tableName = "expense_table",
        foreignKeys =
        @ForeignKey(entity = Trip.class, parentColumns = "id", childColumns = "tripId",
        onDelete = ForeignKey.CASCADE)
)
public class Expense {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String mId;

    @ColumnInfo(name = "tripId")
    private String mTripId;

    @ColumnInfo(name = "expenseName")
    private String mExpenseName;

    @ColumnInfo(name = "cost")
    private float mCost;

    @ColumnInfo(name = "BitMapID")
    private int mBitMapID;

    @ColumnInfo(name = "type")
    private String mType;

    @Ignore
    public Expense(String tripId, String expenseName, float cost, int bitMapID, String type)
    {
        mId = UUID.randomUUID().toString();
        mTripId = tripId;
        mExpenseName = expenseName;
        mCost = cost;
        mBitMapID = bitMapID;
        mType = type;
    }

    public Expense(String id, String tripId, String expenseName, float cost, int bitMapID, String type)
    {
        mId = id;
        mTripId = tripId;
        mExpenseName = expenseName;
        mCost = cost;
        mBitMapID = bitMapID;
        mType = type;
    }

    public String getId() { return mId; }
    public String getTripId() { return mTripId; }
    public String getExpenseName() { return mExpenseName; }
    public float getCost() { return mCost; }
    public int getBitMapID() { return mBitMapID; }
    public String getType() { return mType; }
}
