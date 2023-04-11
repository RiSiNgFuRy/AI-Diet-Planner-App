package com.example.aidietplanner_v1.Kotlin.ViewModel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aidietplanner_v1.Kotlin.Models.*
import com.example.aidietplanner_v1.Kotlin.Utils.SharedPrefs
import com.example.aidietplanner_v1.Services.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

class SettingsViewModel: ViewModel() {

    private val _genderOptions =  MutableLiveData<Response<GenderOptionsResponseModel>>()
    val genderOptions: LiveData<Response<GenderOptionsResponseModel>> = _genderOptions

    private val _foodPreferenceOptions = MutableLiveData<Response<FoodPreferenceOptionsResponseModel>>()
    val foodPreferenceOptions: LiveData<Response<FoodPreferenceOptionsResponseModel>> = _foodPreferenceOptions

    private val _goalOptions = MutableLiveData<Response<ArrayList<GoalOptionsModel>>>()
    val goalOptions: LiveData<Response<ArrayList<GoalOptionsModel>>> = _goalOptions

    private val _setBmiDetails = MutableLiveData<Response<BMIDetailsModel>>()
    val setBmiDetails: LiveData<Response<BMIDetailsModel>> = _setBmiDetails

    private val _setUserGender = MutableLiveData<Response<SuccessStatusCommonModel>>()
    val setUserGender: LiveData<Response<SuccessStatusCommonModel>> = _setUserGender

    private val _setUserAge = MutableLiveData<Response<UserAgeResponseModel>>()
    val setUserAge: LiveData<Response<UserAgeResponseModel>> = _setUserAge

    private val _setUserGoal = MutableLiveData<Response<SuccessStatusCommonModel>>()
    val setUserGoal: LiveData<Response<SuccessStatusCommonModel>> = _setUserGoal

    private val _setUserFoodPreference = MutableLiveData<Response<SuccessStatusCommonModel>>()
    val setUserFoodPreference: LiveData<Response<SuccessStatusCommonModel>> = _setUserFoodPreference


    fun getGenderOptions() {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val request = async { RetrofitClient.buildService().getGenderTypes() }
                val response = request.await()
                _genderOptions.postValue(response)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun getFoodPreferenceOptions() {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val request = async { RetrofitClient.buildService().getFoodPreferences() }
                val response = request.await()
                _foodPreferenceOptions.postValue(response)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun getGoalOptions() {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val request = async { RetrofitClient.buildService().getAllGoalTypes() }
                val response = request.await()
                _goalOptions.postValue(response)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun setBmiDetails(userId: String?, bmiDetailsRequestModel: BmiDetailsRequestModel?){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val request = async { RetrofitClient.buildService().setUserBmiDetails(userId!!, bmiDetailsRequestModel!!) }
                val response = request.await()
                _setBmiDetails.postValue(response)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun setUserGender(userId: String?, genderId: String?){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val request = async { RetrofitClient.buildService().setUserGenderType(userId!!, genderId!!) }
                val response = request.await()
                _setUserGender.postValue(response)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun setUserAge(userId: String?, age: Int?){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val request = async { RetrofitClient.buildService().setUserAge(userId!!, age!!) }
                val response = request.await()
                _setUserAge.postValue(response)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun setUserGoal(userId: String?, goalId: String?){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val request = async { RetrofitClient.buildService().setUserGoalType(userId!!, goalId!!) }
                val response = request.await()
                _setUserGoal.postValue(response)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun setUserFoodPreference(userId: String?, foodPreferenceId: String?){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val request = async { RetrofitClient.buildService().setUserFoodPreferenceType(userId!!, foodPreferenceId!!) }
                val response = request.await()
                _setUserFoodPreference.postValue(response)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}