package com.example.aidietplanner_v1.Kotlin.Models

import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants
import com.google.gson.annotations.SerializedName

class GenderOptionsModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("imgurl")
    val imgurl: String
):BaseModel() {
    override fun getModel(): Int {
        return Constants.USER_GENDER_OPTIONS
    }

}