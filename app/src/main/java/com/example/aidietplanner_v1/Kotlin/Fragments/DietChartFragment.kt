package com.example.aidietplanner_v1.Kotlin.Fragments

import android.os.BaseBundle
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aidietplanner_v1.Kotlin.Adapter.DietChartAdapter
import com.example.aidietplanner_v1.Kotlin.Models.BMIResultModel
import com.example.aidietplanner_v1.Kotlin.Models.DietChartModel
import com.example.aidietplanner_v1.Kotlin.Models.MealModel
import com.example.aidietplanner_v1.Kotlin.Models.MealsListModel
import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.SharedPrefs
import com.example.aidietplanner_v1.Kotlin.ViewModel.DietChartViewModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.databinding.FragmentDietChartBinding

class DietChartFragment : Fragment() {

    private lateinit var binding: FragmentDietChartBinding

    private lateinit var sharedPrefs: SharedPrefs

    private lateinit var dietChartViewModel: DietChartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDietChartBinding.inflate(inflater, container, false)
        dietChartViewModel = ViewModelProvider(requireActivity()).get(DietChartViewModel::class.java)
        sharedPrefs = SharedPrefs(requireActivity(), getString(R.string.shared_pref_key))

        fetchUserDietChart()

        binding.apply {
            messageBoxBtn.setOnClickListener {
                fetchUserDietChart()
            }

            dietChartFragList.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = DietChartAdapter(requireActivity(), getData())
            }
        }

        return binding.root
    }

    private fun fetchUserDietChart() {
         with(sharedPrefs) {
            getRequiredAiSettings()?.let {
                dietChartViewModel.getUserDietChart(getUserId()!!)
            } ?: Toast.makeText(activity, getString(R.string.settings_incomplete_message), Toast.LENGTH_SHORT).show()
        }
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

//        val data = arrayListOf<BaseModel>()
//
//        dietChartViewModel.userDietChart.observe(requireActivity()) { response ->
//            if (response.isSuccessful && response.body() != null){
//                listOfMeals.clear()
//                data.add(DietChartModel("Diet Chart", arrayListOf<BaseModel>()))
//                binding.apply {
//                    messageBox.visibility = View.GONE
//                    dietChartFragList.adapter?.notifyDataSetChanged()
//                }
//            }else if (response.body() == null) {
//                binding.apply {
//                    dietChartFragList.visibility = View.GONE
//                    messageBox.visibility = View.VISIBLE
//                }
//            }else {
//                Toast.makeText(activity, "Some error occurred2", Toast.LENGTH_SHORT).show()
//            }
//            binding.progressBar.visibility = View.GONE
//        }
//
//        getBmiResult()?.let { data.add(it) }

        return arrayListOf(
            BMIResultModel("BMI: 18", "Healthy"),
            DietChartModel("Diet Chart", listOfMeals)
        )
    }

    private fun getBmiResult(): BMIResultModel? {
        return with(sharedPrefs) {
            if(getUserHeight()!=null && getUserWeight()!=null) {
                val bmiValue:Double = Integer.parseInt(getUserWeight()!!).toDouble() / Integer.parseInt(getUserHeight()!!).toDouble() *100
                val result = bmiValue.let {
                    if(it < 18.5)
                        getString(R.string.under_weight)
                    else if(it < 25)
                        getString(R.string.healthy)
                    else if(it < 30)
                        getString(R.string.over_weight)
                    else
                        getString(R.string.obesity)
                }
                return@with BMIResultModel(
                    String.format("%.2f",bmiValue),
                    result
                )
            }
            null
        }
    }

}