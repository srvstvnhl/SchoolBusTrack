package com.example.nihal.navigationdrawerexample.login;

import com.example.nihal.navigationdrawerexample.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class LoginBuilder  {

    private static Retrofit retrofit;

    public static LoginInterface getLoginInstance(){

        if (retrofit==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BaseUrl)
                    .addConverterFactory(GsonConverterFactory
                            .create()).build();
        }
        return retrofit.create(LoginInterface.class);
    }

}
