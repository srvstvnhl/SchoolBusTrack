package com.example.nihal.navigationdrawerexample.login;

import com.google.gson.annotations.SerializedName;

public class ResponseResult {

    @SerializedName("client_id")
    private Integer clientId;

    @SerializedName("user_id")
    private Integer userId;

    @SerializedName("user_name")
    private String username;

    @SerializedName("user_password")
    private String password;

    @SerializedName("user_image")
    private String userImage;

    @SerializedName("user_type")
    private Integer userType;


    public ResponseResult(Integer clientId, Integer userId, String username, String password, String userImage, Integer userType) {
        this.clientId = clientId;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userImage = userImage;
        this.userType = userType;
    }

    public ResponseResult(String username, String password) {

    this.username = username;
    this.password=password;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
