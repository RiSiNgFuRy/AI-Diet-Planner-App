package com.example.aidietplanner_v1.Kotlin.Binders

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Adapter.DietChartAdapter
import com.example.aidietplanner_v1.Kotlin.Adapter.GenericAdapter
import com.example.aidietplanner_v1.Kotlin.Adapter.MealsListAdapter
import com.example.aidietplanner_v1.Kotlin.Models.DietChartModel
import com.example.aidietplanner_v1.databinding.CardLayoutGenderOptionsBinding
import com.example.aidietplanner_v1.databinding.CardLayoutSettingsListBinding

class DietChartBinder(val adapter: GenericAdapter, val activity: FragmentActivity): DataBinder<DietChartBinder.DietChartViewHolder>() {

    inner class DietChartViewHolder(val binding: CardLayoutSettingsListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: DietChartModel){
            binding.apply {
                cardHeading.text = data.heading
                cardList.apply {
                    layoutManager = LinearLayoutManager(activity)
                    isNestedScrollingEnabled = false
                    adapter = MealsListAdapter(activity, data.listOfMeals)
                }
            }
        }
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardLayoutSettingsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DietChartViewHolder(binding)
    }

    override fun bindViewHolder(holder: DietChartViewHolder, position: Int) {
        val data = adapter.getData(position) as DietChartModel
        holder.bind(data)
    }
}