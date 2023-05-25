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
import com.example.aidietplanner_v1.Kotlin.Utils.Constants
import com.example.aidietplanner_v1.Kotlin.Utils.SharedPrefs
import com.example.aidietplanner_v1.Kotlin.ViewModel.DietChartViewModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.databinding.FragmentDietChartBinding
import kotlin.math.pow

class DietChartFragment : Fragment() {

    private lateinit var binding: FragmentDietChartBinding

    private lateinit var sharedPrefs: SharedPrefs

    private lateinit var dietChartViewModel: DietChartViewModel

    private var data = arrayListOf<BaseModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDietChartBinding.inflate(inflater, container, false)
        dietChartViewModel = ViewModelProvider(requireActivity()).get(DietChartViewModel::class.java)
        sharedPrefs = SharedPrefs(requireActivity(), getString(R.string.shared_pref_key))

//        if(!sharedPrefs.getAlreadyFetchedDietChart()) {
            fetchUserDietChart()
//        }
        addObservers()

        binding.apply {
            messageBoxBtn.setOnClickListener {
                fetchUserDietChart()
            }

            dietChartFragList.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = DietChartAdapter(requireActivity(), data)
            }
        }

        return binding.root
    }

    private fun fetchUserDietChart() {
         with(sharedPrefs) {
            getRequiredAiSettings()?.let {
                dietChartViewModel.getUserExistingDietChart(getUserId()!!)
                binding.progressBar.visibility = View.VISIBLE
            } ?: Toast.makeText(activity, getString(R.string.settings_incomplete_message), Toast.LENGTH_SHORT).show()
        }
    }
    private fun addObservers() {

        dietChartViewModel.userDietChart.observe(requireActivity()) { response ->
            var listOfMeals = arrayListOf<BaseModel>()
            if (response.isSuccessful && response.body() != null){
                val responseData = response.body()
                data.clear()
                getBmiResult()?.let { data.add(it) }
                responseData?.run {
                    if(breakfast!=null)
                        listOfMeals.add(MealsListModel("Breakfast", breakfast))
                    if(lunch!=null)
                        listOfMeals.add(MealsListModel("Lunch", lunch))
                    if(dinner!=null)
                        listOfMeals.add(MealsListModel("Dinner", dinner))
                }
                data.add( DietChartModel("Diet Chart", listOfMeals) )
                binding.apply {
                    messageBox.visibility = View.GONE
                    dietChartFragList.adapter?.notifyItemChanged(Constants.USER_DIET_CHART)
                }
//                sharedPrefs.setHasAlreadyFetchedDietChart(true)
            }else if (response.body() == null) {
                binding.apply {
                    dietChartFragList.visibility = View.GONE
                    messageBox.visibility = View.VISIBLE
                    messageBox.visibility = View.GONE
                }
            }else {
                Toast.makeText(activity, "Some error occurred", Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun getRandomList(arrayList: ArrayList<MealModel>, numberOfOutputs: Int): ArrayList<MealModel> {
        return arrayList.let {
            it.shuffled()
            for (i in 0..(it.size - 1 - numberOfOutputs))
                it.removeAt(0)
            it
        }
    }
    private fun getBmiResult(): BMIResultModel? {
        return with(sharedPrefs) {
            if(getUserHeight()!=null && getUserWeight()!=null) {
                val userWeight = Integer.parseInt(getUserWeight() ?: "0")
                val userHeight = Integer.parseInt(getUserHeight() ?: "0")
                val userAge = Integer.parseInt(getUserAge() ?: "0")
                val userGender = getUserGenderValue() ?: "Male"
                val bmiValue: Double =  userWeight.toDouble()/userHeight.toDouble().pow(2)  *10000
                val bmrValue: Double =  when(userGender) {
                    "Male" -> {
                        ((9.99 * userWeight) + (6.25 * userHeight) - (5 * userAge) +5) / 1000
                    }
                    "Female" -> {
                        ((9.99 * userWeight) + (6.25 * userHeight) - (5 * userAge) - 161) / 1000
                    }
                    else -> { 0.0 }
                }
                val imgId = bmiValue.let {
                    if(it < 18.5)
                        R.drawable.underweight
                    else if(it < 25)
                        R.drawable.healthy
                    else if(it < 30)
                        R.drawable.overweight
                    else
                        R.drawable.obesity
                }
                return@with BMIResultModel(
                    userHeight.toString(),
                    userWeight.toString(),
                    String.format("%.0f",bmiValue),
                    String.format("%.2f",bmrValue),
                    imgId
                )
            }
            null
        }
    }

}