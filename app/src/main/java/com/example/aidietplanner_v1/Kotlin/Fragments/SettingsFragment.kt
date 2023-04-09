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

        binding.settingsList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = SettingsAdapter(requireActivity(), getList())
        }

        return binding.root
    }

    private fun getSettingOptions(){

        settingsViewModel.getGenderOptions()
        settingsViewModel.getFoodPreferenceOptions()
        settingsViewModel.getGoalOptions()

    }

    private fun getList(): ArrayList<BaseModel>{
        var foodOptions = arrayListOf<BaseModel>()
        settingsViewModel.foodPreferenceOptions.observe(requireActivity()) {response ->
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
            BMIDetailsModel("182cm", "80kg"),
            UserGenderSelectionModel("Gender", genderOptions),
            UserAgePickerModel("Age", 24),
            UserGoalSelectionModel("Goal", goalOptions),
            UserFoodPreferencesModel("Food Preferences", foodOptions),
            LogOutBtnModel()
        )
    }
}
