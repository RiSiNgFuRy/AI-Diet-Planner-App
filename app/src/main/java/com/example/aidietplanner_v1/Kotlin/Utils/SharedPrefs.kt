package com.example.aidietplanner_v1.Kotlin.Utils

import android.content.Context

class SharedPrefs(context: Context, key: String) {
    private val sharedPrefs = context.getSharedPreferences(key, Context.MODE_PRIVATE)

    fun setUserCredentials(token: String, userName: String, userId: String, userEmail: String){
        sharedPrefs.edit()
            .putString("token", token)
            .putString("userName", userName)
            .putString("userId", userId)
            .putString("userEmail", userEmail)
            .commit()
    }

    fun setUserGender(id: String?) {
        sharedPrefs.edit()
            .putString("userGender", id)
            .commit()
    }

    fun setUserFoodPreference(id: String?){
        sharedPrefs.edit()
            .putString("userFoodPreference", id)
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

    fun getUserFoodPreference(): String? {
        return sharedPrefs.getString("userFoodPreference", null)
    }

    fun clearSharedPrefs() {
        sharedPrefs
            .edit()
            .clear()
            .commit()
    }
}