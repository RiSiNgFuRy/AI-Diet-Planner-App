package com.example.aidietplanner_v1.Kotlin.Models

import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants
import com.google.gson.annotations.SerializedName

class UserAgePickerModel(
    var heading: String,
):BaseModel() {
    override fun getModel(): Int {
        return Constants.USER_AGE_PICKER
    }
}