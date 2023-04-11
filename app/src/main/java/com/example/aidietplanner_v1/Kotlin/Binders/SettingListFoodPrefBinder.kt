package com.example.aidietplanner_v1.Kotlin.Binders

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Adapter.GenericAdapter
import com.example.aidietplanner_v1.Kotlin.Helper.ImageHelper
import com.example.aidietplanner_v1.Kotlin.Models.FoodPreferencesOptionsModel
import com.example.aidietplanner_v1.Kotlin.Utils.SharedPrefs
import com.example.aidietplanner_v1.Kotlin.ViewModel.SettingsViewModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.databinding.CardLayoutFoodPrefOptionsBinding


class SettingListFoodPrefBinder(val activity: FragmentActivity, private val adapter: GenericAdapter): DataBinder<SettingListFoodPrefBinder.SettingListFoodPrefViewHolder>() {

    lateinit var  settingsViewModel: SettingsViewModel
    lateinit var sharedPrefs: SharedPrefs
    inner class SettingListFoodPrefViewHolder(private val binding: CardLayoutFoodPrefOptionsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: FoodPreferencesOptionsModel, position: Int){
            binding.apply {
                foodPrefTypeName.text = data.type
                ImageHelper.loadImage(activity, foodPrefTypeImg, data.mainImgUrl)
                ImageHelper.loadImageFit(activity, foodPrefTypeLogo, data.logoImgUrl)

                if (sharedPrefs.getUserFoodPreference() == data.id) {
                    itemCard.setBackgroundColor(activity.resources.getColor(R.color.primaryColor))
                    foodPrefTypeName.setTextColor(activity.resources.getColor( R.color.white ))
                } else {
                    itemCard.setBackgroundColor(activity.resources.getColor(R.color.e3e3e3))
                    foodPrefTypeName.setTextColor(activity.resources.getColor(R.color.black))
                }
            }
            binding.itemCard.setOnClickListener {
                if(sharedPrefs.getUserFoodPreference() != data.id)
                    settingsViewModel.setUserFoodPreference(sharedPrefs.getUserId(), data.id)
            }
        }
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        settingsViewModel = ViewModelProvider(activity)[SettingsViewModel::class.java]
        sharedPrefs = SharedPrefs(activity, activity.getString(R.string.shared_pref_key))
        val binding = CardLayoutFoodPrefOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingListFoodPrefViewHolder(binding)
    }

    override fun bindViewHolder(holder: SettingListFoodPrefViewHolder, position: Int) {
        val data = adapter.getData(position) as FoodPreferencesOptionsModel
        holder.bind(data, position)
    }

}