package com.example.aidietplanner_v1.Kotlin.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aidietplanner_v1.Kotlin.Models.DietChartModel
import com.example.aidietplanner_v1.Kotlin.Models.DietChartResponseModel
import com.example.aidietplanner_v1.Services.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

class DietChartViewModel: ViewModel() {
    private val _userDietChart = MutableLiveData<Response<DietChartResponseModel>>()
    val userDietChart:LiveData<Response<DietChartResponseModel>> = _userDietChart

    fun getUserExistingDietChart(userId: String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                var request = async { RetrofitClient.buildService().getUserDietChart(userId, "existing") }
                var response = request.await()
                _userDietChart.postValue(response)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun getUserNewDietChart(userId: String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                var request = async { RetrofitClient.buildService().getUserDietChart(userId, "new") }
                var response = request.await()
                _userDietChart.postValue(response)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

//    fun changedUserBmiBmr() {
//
//    }
}