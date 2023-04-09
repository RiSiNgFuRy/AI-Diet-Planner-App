package com.example.aidietplanner_v1.Kotlin.Models

import android.util.ArraySet
import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants

class DietChartModel(val heading: String="", val listOfMeals: ArrayList<BaseModel> = arrayListOf()):BaseModel() {
    override fun getModel(): Int {
        return Constants.USER_DIET_CHART
    }
}
