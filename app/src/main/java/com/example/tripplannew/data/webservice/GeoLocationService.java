package com.example.tripplannew.data.webservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoLocationService {

    @GET("https://us1.locationiq.com/v1/nearby.php")
    Call<List<Place>> getNearbyPlaces(
            @Query("key") String key,
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("tag") String tag,
            @Query("radius") int radius
    );

    @GET("https://us1.locationiq.com/v1/reverse.php?format=json")
    Call<Place> getPlaceInfo(
            @Query("key") String key,
            @Query("lat") double lat,
            @Query("lon") double lon
    );
}
