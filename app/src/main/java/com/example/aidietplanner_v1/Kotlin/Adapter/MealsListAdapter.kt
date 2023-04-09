package com.example.aidietplanner_v1.Kotlin.Adapter

import androidx.fragment.app.FragmentActivity
import com.example.aidietplanner_v1.Kotlin.Binders.MealsListBinder
import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants

class MealsListAdapter(activity: FragmentActivity, data: ArrayList<BaseModel>): GenericAdapter(data) {
    init {
        addBinder(Constants.TYPES_OF_MEALS, MealsListBinder(this, activity))
    }
}