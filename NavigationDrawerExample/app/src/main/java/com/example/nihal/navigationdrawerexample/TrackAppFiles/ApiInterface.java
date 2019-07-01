package com.example.nihal.navigationdrawerexample.TrackAppFiles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    @GET("mobile_api/static_api/gps.php")
    Call<List<TrackObject>> getLocation();
}
