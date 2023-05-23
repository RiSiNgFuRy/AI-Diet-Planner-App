package com.example.aidietplanner_v1.Kotlin.Utils

import android.content.Context

class SharedPrefs(context: Context, key: String) {
    private val sharedPrefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)

    fun setUserCredentials(
        token: String,
        userName: String,
        userId: String,
        userEmail: String,
        userHeight: String?,
        userWeight: String?,
        userGender: String?,
        userAge: String?,
        userGoal: String?,
        userFoodPreference: String?
    ){
        sharedPrefs.edit()
            .putString("token", token)
            .putString("userName", userName)
            .putString("userId", userId)
            .putString("userEmail", userEmail)
            .putString("userHeight", userHeight)
            .putString("userWeight", userWeight)
            .putString("userGender", userGender)
            .putString("userAge", userAge)
            .putString("userGoal", userGoal)
            .putString("userFoodPreference", userFoodPreference)
            .commit()
    }

    fun setUserGender(id: String?) {
        sharedPrefs.edit()
            .putString("userGender", id)
            .commit()
    }

    fun setUserGenderValue(value: String) {
        sharedPrefs.edit()
            .putString("userGenderValue", value)
            .commit()
    }

    fun setUserFoodPreference(id: String?){
        sharedPrefs.edit()
            .putString("userFoodPreference", id)
            .commit()
    }

    fun setUserHeight(height: String) {
        sharedPrefs.edit()
            .putString("userHeight", height)
            .commit()
    }

    fun setUserWeight(weight: String?) {
        sharedPrefs.edit()
            .putString("userWeight", weight)
            .commit()
    }

    fun setUserAge(age: String?){
        sharedPrefs.edit()
            .putString("userAge", age)
            .commit()
    }

    fun setUserGoal(goalId: String?){
        sharedPrefs.edit()
            .putString("userGoal", goalId)
            .commit()
    }

    fun setHasAlreadyFetchedDietChart(bool: Boolean) {
        sharedPrefs.edit()
            .putBoolean("alreadyFetched", bool)
            .commit()
    }

    fun getUserName(): String? {
        return sharedPrefs.getString("userName", null)
    }

    fun getUserId(): String? {
        return sharedPrefs.getString("userId", null)
    }

    fun getUserEmail(): String? {
        return sharedPrefs.getString("userEmail", null)
    }

    fun getToken(): String? {
        return sharedPrefs.getString("token", null)
    }

    fun getUserGender(): String? {
        return sharedPrefs.getString("userGender", null)
    }

    fun getUserGenderValue(): String? {
        return sharedPrefs.getString("userGenderValue", null)
    }

    fun getUserFoodPreference(): String? {
        return sharedPrefs.getString("userFoodPreference", null)
    }

    fun getGoal(): String? {
        return sharedPrefs.getString("userGoal", null)
    }
    fun getUserHeight(): String? {
        return sharedPrefs.getString("userHeight", null)
    }

    fun getUserWeight(): String? {
        return sharedPrefs.getString("userWeight", null)
    }

    fun getUserAge(): String? {
        return sharedPrefs.getString("userAge", null)
    }

    fun getRequiredAiSettings(): Boolean? {
         return with(sharedPrefs) {
             val checkValues =
                 getUserHeight()?.let {
                     getUserWeight()?.let {
                         getUserAge()?.let {
                             getGoal()?.let {
                                 getUserFoodPreference()?.let {
                                     true
                                 }
                             }
                         }
                     }
                 }
             checkValues
         }
    }

    fun getAlreadyFetchedDietChart(): Boolean {
        return sharedPrefs.getBoolean("alreadyFetched", false)
    }

    fun clearSharedPrefs() {
        sharedPrefs
            .edit()
            .clear()
            .commit()
    }
}