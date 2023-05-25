package com.example.aidietplanner_v1.Kotlin.Models

import com.google.gson.annotations.SerializedName

data class MealModel(
    @SerializedName("foodName")
    val foodName: String,
    @SerializedName("proteins")
    val proteins: Double,
    @SerializedName("carbohydrates")
    val carbohydrates: Double,
    @SerializedName("fats")
    val fats: Double,
    @SerializedName("calories")
    val calories: Double,
    @SerializedName("iron")
    val iron: Double,
    @SerializedName("calcium")
    val calcium: Double,
    @SerializedName("sodium")
    val sodium: Double,
    @SerializedName("potassium")
    val potassium: Double,
    @SerializedName("fibre")
    val fibre: Double,
    @SerializedName("vitamind")
    val vitamind: Double,
    @SerializedName("sugars")
    val sugars: Double
)

