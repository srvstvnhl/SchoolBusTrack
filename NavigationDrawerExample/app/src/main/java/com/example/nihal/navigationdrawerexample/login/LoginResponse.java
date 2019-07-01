package com.example.nihal.navigationdrawerexample.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("result")
    private ResponseResult responseResult;

    @SerializedName("code")
    private Integer code;


    public LoginResponse(String message, ResponseResult responseResult, Integer code) {
        this.message = message;
        this.responseResult = responseResult;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseResult getResponseResult() {
        return responseResult;
    }

    public void setResponseResult(ResponseResult responseResult) {
        this.responseResult = responseResult;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
