package com.example.aidietplanner_v1.Kotlin.Models

import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants
import com.google.gson.annotations.SerializedName

data class MealsListModel(
    @SerializedName("mealType")
    val mealType: String="",
    @SerializedName("listOfMeals")
    val listOfMeals: ArrayList<MealModel>
    ): BaseModel()
{
    override fun getModel(): Int {
        return Constants.TYPES_OF_MEALS
    }
}