package com.example.aidietplanner_v1.Kotlin.Models

import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants
import com.google.gson.annotations.SerializedName

class FoodPreferencesOptionsModel(
    @SerializedName("type")
    val type: String="",
    @SerializedName("mainImgUrl")
    val mainImgUrl: String="",
    @SerializedName("logoImgUrl")
    val logoImgUrl: String=""
): BaseModel() {
    override fun getModel(): Int {
        return Constants.USER_FOOD_PREFERENCES_OPTIONS
    }

}