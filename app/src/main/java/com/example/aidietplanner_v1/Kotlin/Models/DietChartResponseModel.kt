package com.example.aidietplanner_v1.Kotlin.Models

import com.google.gson.annotations.SerializedName

data class DietChartResponseModel(
    @SerializedName("Breakfast")
    val breakfast: ArrayList<MealModel>?,
    @SerializedName("Lunch")
    val lunch: ArrayList<MealModel>?,
    @SerializedName("Dinner")
    val dinner: ArrayList<MealModel>?
)
