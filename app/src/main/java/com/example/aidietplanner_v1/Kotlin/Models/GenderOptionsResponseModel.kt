package com.example.aidietplanner_v1.Kotlin.Models

import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.google.gson.annotations.SerializedName

data class GenderOptionsResponseModel(
    @SerializedName("data")
    val data: ArrayList<GenderOptionsModel>
)