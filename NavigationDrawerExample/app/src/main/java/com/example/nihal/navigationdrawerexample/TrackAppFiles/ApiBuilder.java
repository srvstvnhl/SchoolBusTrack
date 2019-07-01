package com.example.nihal.navigationdrawerexample.TrackAppFiles;

import com.example.nihal.navigationdrawerexample.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiBuilder {


    private static Retrofit retrofit;

    public static ApiInterface getInstance(){

        if (retrofit==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BaseUrl)
                    .addConverterFactory(GsonConverterFactory
                            .create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }

}
