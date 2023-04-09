package com.example.aidietplanner_v1.Kotlin.Models

import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants

class MealsListModel(val mealType: String="", val listOfMeals: ArrayList<BaseModel> = arrayListOf()):BaseModel() {
    override fun getModel(): Int {
        return Constants.TYPES_OF_MEALS
    }
}