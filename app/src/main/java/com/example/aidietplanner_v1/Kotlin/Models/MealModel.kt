package com.example.aidietplanner_v1.Kotlin.Models

import com.google.gson.annotations.SerializedName

data class MealModel(
    @SerializedName("foodName")
    val foodName: String,
    @SerializedName("protiens")
    val protiens: String,
    @SerializedName("carbohydrates")
    val carbohydrates: String,
    @SerializedName("fats")
    val fats: String,
    @SerializedName("calories")
    val calories: String
)
