package com.example.aidietplanner_v1.Kotlin.Adapter

import androidx.fragment.app.FragmentActivity
import com.example.aidietplanner_v1.Kotlin.Binders.BMIResultBinder
import com.example.aidietplanner_v1.Kotlin.Binders.DietChartBinder
import com.example.aidietplanner_v1.Kotlin.Fragments.DietChartFragment
import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants

class DietChartAdapter(activity: FragmentActivity, data: ArrayList<BaseModel>): GenericAdapter(data) {
    init {
        addBinder(Constants.USER_DIET_CHART, DietChartBinder(this, activity))
        addBinder(Constants.USER_BMI_RESULTS, BMIResultBinder(this, activity))
    }
}