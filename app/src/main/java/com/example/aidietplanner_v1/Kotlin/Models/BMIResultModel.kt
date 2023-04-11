package com.example.aidietplanner_v1.Kotlin.Models

import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants

class BMIResultModel(val bmiValue: String, val bmiResultTxt: String): BaseModel() {
    override fun getModel(): Int {
        return Constants.USER_BMI_RESULTS
    }
}