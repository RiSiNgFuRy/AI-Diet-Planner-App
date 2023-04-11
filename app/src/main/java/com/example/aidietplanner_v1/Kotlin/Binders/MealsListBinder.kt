package com.example.aidietplanner_v1.Kotlin.Binders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Adapter.GenericAdapter
import com.example.aidietplanner_v1.Kotlin.Adapter.MealTypeListAdapter
import com.example.aidietplanner_v1.Kotlin.Adapter.MealsListAdapter
import com.example.aidietplanner_v1.Kotlin.Models.MealsListModel
import com.example.aidietplanner_v1.databinding.CardLayoutSettingsListBinding
import com.example.aidietplanner_v1.databinding.CardLayoutTypesOfMealBinding

class MealsListBinder(val adapter: GenericAdapter,val activity: FragmentActivity): DataBinder<MealsListBinder.MealsListViewHolder>() {

    inner class MealsListViewHolder(val binding: CardLayoutTypesOfMealBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: MealsListModel){
            binding.apply {
                cardHeading.text = data.mealType
                cardList.apply {
                    layoutManager = LinearLayoutManager(activity)
                    isNestedScrollingEnabled = false
                    adapter = MealTypeListAdapter(activity, data.listOfMeals)
                }
            }
        }
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardLayoutTypesOfMealBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MealsListViewHolder(binding)
    }

    override fun bindViewHolder(holder: MealsListViewHolder, position: Int) {
        val data = adapter.getData(position) as MealsListModel
        holder.bind(data)
    }
}