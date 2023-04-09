package com.example.aidietplanner_v1.Java.Models;

import com.google.gson.annotations.SerializedName;

public class LoginTokenVerificationModel {
    @SerializedName("token")
    String token;

    public LoginTokenVerificationModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
