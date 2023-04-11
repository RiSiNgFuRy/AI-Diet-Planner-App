package com.example.aidietplanner_v1.Kotlin.Fragments

import android.os.BaseBundle
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aidietplanner_v1.Kotlin.Adapter.DietChartAdapter
import com.example.aidietplanner_v1.Kotlin.Models.BMIResultModel
import com.example.aidietplanner_v1.Kotlin.Models.DietChartModel
import com.example.aidietplanner_v1.Kotlin.Models.MealModel
import com.example.aidietplanner_v1.Kotlin.Models.MealsListModel
import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.databinding.FragmentDietChartBinding

class DietChartFragment : Fragment() {

    private lateinit var binding: FragmentDietChartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDietChartBinding.inflate(inflater, container, false)
        binding.dietChartFragList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DietChartAdapter(requireActivity(), getData())
        }
        return binding.root
    }

    private fun getData(): ArrayList<BaseModel>{
        var breakfastList = arrayListOf<MealModel>(
            MealModel("Avocados", "2","8.5","15","160"),
            MealModel("Bananas", "0.3","23","0.3","89"),
            MealModel("Berries", "14","77","0.4","349"),
            )

        var lunchList = arrayListOf<MealModel>(
            MealModel("Avocados", "2","8.5","15","160"),
            MealModel("Bananas", "0.3","23","0.3","89"),
            MealModel("Berries", "14","77","0.4","349"),
        )

        var dinnerList = arrayListOf<MealModel>(
            MealModel("Avocados", "2","8.5","15","160"),
            MealModel("Bananas", "0.3","23","0.3","89"),
            MealModel("Berries", "14","77","0.4","349"),
        )

        var listOfMeals = arrayListOf<BaseModel>(
            MealsListModel("Breakfast", breakfastList),
            MealsListModel("Lunch", lunchList),
            MealsListModel("Dinner", dinnerList)
        )

        return arrayListOf(
            BMIResultModel("18", "Healthy"),
            DietChartModel("Diet Chart", listOfMeals)
        )
    }

}