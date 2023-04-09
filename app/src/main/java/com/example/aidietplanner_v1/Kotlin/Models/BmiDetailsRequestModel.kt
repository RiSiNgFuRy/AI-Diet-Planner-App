package com.example.aidietplanner_v1.Kotlin.Models

import com.google.gson.annotations.SerializedName

data class BmiDetailsRequestModel(
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int
)
