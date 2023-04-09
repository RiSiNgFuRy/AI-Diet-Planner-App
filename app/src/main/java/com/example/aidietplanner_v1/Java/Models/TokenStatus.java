package com.example.aidietplanner_v1.Java.Models;

import com.google.gson.annotations.SerializedName;

public class TokenStatus {
    @SerializedName("status")
    String status;

    public TokenStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
