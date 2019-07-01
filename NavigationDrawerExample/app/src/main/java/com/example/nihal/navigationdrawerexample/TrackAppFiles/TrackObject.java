package com.example.nihal.navigationdrawerexample.TrackAppFiles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackObject {

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("speed")
    @Expose
    private String speed;

    @SerializedName("date")
    @Expose
    private String date;


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
