package com.example.aidietplanner_v1.Kotlin.Models

import com.google.gson.annotations.SerializedName

data class FoodPreferenceOptionsResponseModel(
    @SerializedName("data")
    val data: ArrayList<FoodPreferencesOptionsModel>
)
