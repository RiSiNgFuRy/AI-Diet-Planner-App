package com.example.aidietplanner_v1.Java.Models;

import com.google.gson.annotations.SerializedName;

public class UserLoginResponseModel {
    @SerializedName("status")
    String status;
    @SerializedName("token")
    String token;
    @SerializedName("userId")
    String userId;
    @SerializedName("username")
    String username;
    @SerializedName("email")
    String email;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userid) {
        this.userId = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserLoginResponseModel(String status, String token, String userid, String username, String email) {
        this.status = status;
        this.token = token;
        this.userId = userid;
        this.username = username;
        this.email = email;
    }
}
