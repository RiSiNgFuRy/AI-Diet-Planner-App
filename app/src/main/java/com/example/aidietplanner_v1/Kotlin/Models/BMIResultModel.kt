package com.example.aidietplanner_v1.Kotlin.Models

import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants

class BMIResultModel(val height: String, val weight: String, val bmiValue: String, val bmrValue: String, val imgDrawableId: Int): BaseModel() {
    override fun getModel(): Int {
        return Constants.USER_BMI_RESULTS
    }
}