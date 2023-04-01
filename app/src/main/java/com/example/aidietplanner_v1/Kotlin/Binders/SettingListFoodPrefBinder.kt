package com.example.aidietplanner_v1.Kotlin.Binders

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Adapter.GenericAdapter
import com.example.aidietplanner_v1.Kotlin.Helper.ImageHelper
import com.example.aidietplanner_v1.Kotlin.Models.FoodPreferencesOptionsModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.databinding.CardLayoutFoodPrefOptionsBinding

private val TextView.isMnb: Unit
    get() {

    }

class SettingListFoodPrefBinder(val activity: FragmentActivity, private val adapter: GenericAdapter): DataBinder<SettingListFoodPrefBinder.SettingListFoodPrefViewHolder>() {

    private var selectedItem = -1
    inner class SettingListFoodPrefViewHolder(private val binding: CardLayoutFoodPrefOptionsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: FoodPreferencesOptionsModel, position: Int){
            binding.apply {
                foodPrefTypeName.text = data.heading
                ImageHelper.loadImage(activity, foodPrefTypeImg, data.img)
                ImageHelper.loadImageFit(activity, foodPrefTypeLogo, data.logo)
                foodPrefTypeName.isMnb
                if (selectedItem == position) {
                    itemCard.setBackgroundColor(activity.resources.getColor(R.color.primaryColor))
                    foodPrefTypeName.setTextColor(activity.resources.getColor( R.color.white ))
                } else {
                    itemCard.setBackgroundColor(activity.resources.getColor(R.color.e3e3e3))
                    foodPrefTypeName.setTextColor(activity.resources.getColor(R.color.black))
                }
            }
            setOnClickListeners(binding, position)
        }
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardLayoutFoodPrefOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingListFoodPrefViewHolder(binding)
    }

    override fun bindViewHolder(holder: SettingListFoodPrefViewHolder, position: Int) {
        val data = adapter.getData(position) as FoodPreferencesOptionsModel
        holder.bind(data, position)
    }

    private fun setOnClickListeners(binding: CardLayoutFoodPrefOptionsBinding, position: Int){
        binding.itemCard.setOnClickListener{
            var prevSelectedItem = selectedItem
            selectedItem = position
            adapter.dataChanged(selectedItem)
            adapter.dataChanged(prevSelectedItem)
        }
    }

}