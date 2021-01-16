package com.example.tripplannew.data.webservice;

import com.squareup.moshi.Json;

public class Place {

    @Json(name = "place_id")
    private String placeId;
    @Json(name = "osm_type")
    private String osmType;
    @Json(name = "osm_id")
    private String osmId;
    @Json(name = "lat")
    private double lat;
    @Json(name = "lon")
    private double lon;
    @Json(name = "tag_type")
    private String tagType;
    @Json(name = "name")
    private String name;
    @Json(name = "distance")
    private long distance;
    @Json(name = "display_name")
    private String displayName;

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getOsmType() {
        return osmType;
    }

    public void setOsmType(String osmType) {
        this.osmType = osmType;
    }

    public String getOsmId() {
        return osmId;
    }

    public void setOsmId(String osmId) {
        this.osmId = osmId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
