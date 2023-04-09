package com.example.aidietplanner_v1.Kotlin.Models

import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants
import com.google.gson.annotations.SerializedName

data class GoalOptionsModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String
)
