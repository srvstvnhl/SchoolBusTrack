package com.example.nihal.navigationdrawerexample.login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {

    @FormUrlEncoded
    @POST("mobile_api/static_api/login.php")
    Call<LoginResponse> getUserLogin(@Field("username") String username, @Field("password") String password);

}
