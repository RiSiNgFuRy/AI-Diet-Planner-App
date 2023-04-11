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

    @SerializedName("genderId")
    String genderId;

    @SerializedName("foodPreferenceId")
    String foodPreferenceId;

    @SerializedName("goalId")
    String goalId;

    @SerializedName("age")
    String age;

    @SerializedName("height")
    String height;

    @SerializedName("weight")
    String weight;


    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getFoodPreferenceId() {
        return foodPreferenceId;
    }

    public void setFoodPreferenceId(String foodPreferenceId) {
        this.foodPreferenceId = foodPreferenceId;
    }

    public String getGoalId() {
        return goalId;
    }

    public void setGoalId(String goalId) {
        this.goalId = goalId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

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

    public UserLoginResponseModel(String status, String token, String userId, String username, String email, String genderId, String foodPreferenceId, String goalId, String age, String height, String weight) {
        this.status = status;
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.genderId = genderId;
        this.foodPreferenceId = foodPreferenceId;
        this.goalId = goalId;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }
}
