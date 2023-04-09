package com.example.aidietplanner_v1.Java.Models;

import com.google.gson.annotations.SerializedName;

public class UserRegistrationModel {
    @SerializedName("username")
    String username;
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;

    public UserRegistrationModel(String userName, String emailAddress, String password) {
        this.username = userName;
        this.email = emailAddress;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getEmailAddress() {
        return email;
    }

    public void setEmailAddress(String emailAddress) {
        this.email = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
