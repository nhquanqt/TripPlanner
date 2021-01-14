package com.example.tripplannew.data;

import androidx.annotation.NonNull;
import androidx.media.AudioAttributesCompat;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(
        tableName = "trip_table",
        foreignKeys =
        @ForeignKey(entity = Account.class, parentColumns = "id", childColumns = "userId",
                    onDelete = ForeignKey.CASCADE)
)
public class Trip {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String mId;

    @ColumnInfo(name = "userId")
    private String mUserId;

    @ColumnInfo(name = "tripName")
    private String mTripName;

    @ColumnInfo(name = "budget")
    private float mBudget;

    @ColumnInfo(name = "startDate")
    private String mStartDate;

    @ColumnInfo(name = "endDate")
    private String mEndDate;

    @ColumnInfo(name = "departure")
    private String mDeparture;

    @Ignore
    public Trip(String userId, String tripName, float budget, String startDate, String endDate, String departure)
    {
        mId = UUID.randomUUID().toString();
        mUserId = userId;
        mTripName = tripName;
        mBudget = budget;
        mStartDate = startDate;
        mEndDate = endDate;
        mDeparture = departure;
    }

    public Trip(String id, String userId, String tripName, float budget, String startDate, String endDate, String departure)
    {
        mId = id;
        mUserId = userId;
        mTripName = tripName;
        mBudget = budget;
        mStartDate = startDate;
        mEndDate = endDate;
        mDeparture = departure;
    }

    public String getId() { return mId; }
    public String getUserId() { return mUserId; }
    public String getTripName() { return mTripName; }
    public float getBudget() { return mBudget; }
    public String getStartDate() { return mStartDate; }
    public String getEndDate() { return mEndDate; }
    public String getDeparture() { return mDeparture; }

    public void update(String tripName, float budget, String startDate, String endDate, String departure)
    {
        mTripName = tripName;
        mBudget = budget;
        mStartDate = startDate;
        mEndDate = endDate;
        mDeparture = departure;
    }
}
