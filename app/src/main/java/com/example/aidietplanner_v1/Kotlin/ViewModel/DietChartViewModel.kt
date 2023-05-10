package com.example.aidietplanner_v1.Kotlin.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aidietplanner_v1.Kotlin.Models.DietChartModel
import com.example.aidietplanner_v1.Services.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

class DietChartViewModel: ViewModel() {
    private val _userDietChart = MutableLiveData<Response<DietChartModel>>()
    val userDietChart:LiveData<Response<DietChartModel>> = _userDietChart

    fun getUserDietChart(userId: String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                var request = async { RetrofitClient.buildService().getUserDietChart(userId) }
                var response = request.await()
                _userDietChart.postValue(response)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}