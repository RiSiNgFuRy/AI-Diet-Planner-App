package com.example.aidietplanner_v1.Kotlin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aidietplanner_v1.Java.Activities.MainActivity
import com.example.aidietplanner_v1.Kotlin.Adapter.GoalOptionsAdapter
import com.example.aidietplanner_v1.Kotlin.Adapter.SettingsAdapter
import com.example.aidietplanner_v1.Kotlin.Models.*
import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.Kotlin.Utils.Constants
import com.example.aidietplanner_v1.Kotlin.Utils.SharedPrefs
import com.example.aidietplanner_v1.Kotlin.ViewModel.SettingsViewModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.Services.RetrofitClient
import com.example.aidietplanner_v1.databinding.FragmentSettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    private lateinit var settingsViewModel: SettingsViewModel

    lateinit var sharedPrefs: SharedPrefs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        settingsViewModel = ViewModelProvider(requireActivity()).get(SettingsViewModel::class.java)
        sharedPrefs = SharedPrefs(requireActivity(), requireActivity().getString(R.string.shared_pref_key))
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        getSettingOptions()

        addObservers()

        binding.settingsList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = SettingsAdapter(requireActivity(), getList())
        }

        return binding.root
    }

    private fun addObservers(){
        settingsViewModel.setBmiDetails.observe(requireActivity()){response ->
            if(response.isSuccessful){
                sharedPrefs.apply {
                    setUserHeight(response.body()?.height.toString())
                    setUserWeight(response.body()?.weight.toString())
                }
                binding.settingsList.adapter?.notifyItemChanged(Constants.USER_BMI_DETAILS)
                Toast.makeText(requireActivity().applicationContext, "BMIDetails updated", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireActivity().applicationContext, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        }

        settingsViewModel.setUserGender.observe(requireActivity()){response ->
            if(response.isSuccessful){
                sharedPrefs.apply {
                    setUserGender(response.body()?.id)
                }
                binding.settingsList.adapter?.notifyItemChanged(Constants.USER_GENDER_SELECTION_LIST)
                Toast.makeText(requireActivity(), "User gender updated", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireActivity(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        }

        settingsViewModel.setUserAge.observe(requireActivity()){response ->
            if(response.isSuccessful){
                sharedPrefs.apply {
                    setUserAge(response.body()?.age.toString())
                }
                Toast.makeText(requireActivity(), "User age updated", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireActivity(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        }

        settingsViewModel.setUserGoal.observe(requireActivity()){response ->
            if(response.isSuccessful){
                sharedPrefs.apply {
                    setUserGoal(response.body()?.id!!)
                }
                binding.settingsList.adapter?.notifyItemChanged(Constants.USER_GOAL_LIST)
                Toast.makeText(requireActivity(), "User goal updated", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireActivity(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        }

        settingsViewModel.setUserFoodPreference.observe(requireActivity()){response ->
            if(response.isSuccessful){
                sharedPrefs.apply {
                    setUserFoodPreference(response.body()?.id!!)
                }
                binding.settingsList.adapter?.notifyItemChanged(Constants.USER_FOOD_PREFERENCES_LIST)
                Toast.makeText(requireActivity(), "User food preference updated", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireActivity(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getSettingOptions(){

        settingsViewModel.getGenderOptions()
        settingsViewModel.getFoodPreferenceOptions()
        settingsViewModel.getGoalOptions()

    }

    private fun getList(): ArrayList<BaseModel>{
        var foodOptions = arrayListOf<BaseModel>()
        settingsViewModel.foodPreferenceOptions.observe(requireActivity()) { response ->
            if (response.isSuccessful) {
                foodOptions.clear()
                foodOptions.addAll(response.body()?.data as ArrayList<BaseModel>)
                binding.settingsList.adapter?.notifyItemChanged(5)
            }
        }

        var genderOptions = arrayListOf<BaseModel>()
        settingsViewModel.genderOptions.observe(requireActivity()){ response ->
            if(response.isSuccessful) {
                genderOptions.clear()
                genderOptions.addAll(response.body()?.data as ArrayList<BaseModel>)
                binding.settingsList.adapter?.notifyItemChanged(2)
            }else{
                Toast.makeText(context, getString(R.string.server_error_occurred), Toast.LENGTH_SHORT).show()
            }
        }

        var goalOptions = arrayListOf<GoalOptionsModel>()
        settingsViewModel.goalOptions.observe(requireActivity()) { response ->
            if(response.isSuccessful){
                goalOptions.clear()
                goalOptions.addAll(response.body() as ArrayList<GoalOptionsModel>)
                binding.settingsList.adapter?.notifyItemChanged(4)
            }
        }

        return arrayListOf(
            UserDetailsModel( sharedPrefs.getUserName() ?: "UserName", sharedPrefs.getUserEmail() ?: "xxxgmail.com"),
            BMIDetailsModel(sharedPrefs.getUserHeight() ?: "0", sharedPrefs.getUserWeight() ?: "0"),
            UserGenderSelectionModel("Gender", genderOptions),
            UserAgePickerModel("Age"),
            UserGoalSelectionModel("Goal", goalOptions),
            UserFoodPreferencesModel("Food Preferences", foodOptions),
            LogOutBtnModel()
        )
    }
}
