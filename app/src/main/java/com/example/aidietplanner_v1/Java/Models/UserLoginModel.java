package com.example.aidietplanner_v1.Java.Models;

import com.google.gson.annotations.SerializedName;

public class UserLoginModel {
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;

    public UserLoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
