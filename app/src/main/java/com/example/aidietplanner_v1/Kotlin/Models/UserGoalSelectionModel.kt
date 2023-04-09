package com.example.aidietplanner_v1.Kotlin.Models

import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants

class UserGoalSelectionModel(val heading: String="",val goalsList: ArrayList<GoalOptionsModel> = arrayListOf()): BaseModel() {
    override fun getModel(): Int {
        return Constants.USER_GOAL_LIST
    }

}