package com.example.aidietplanner_v1.Kotlin.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Models.MealModel
import com.example.aidietplanner_v1.databinding.CardLayoutOfMealsBinding

class MealTypeListAdapter(val activity: FragmentActivity, val data: ArrayList<MealModel>): RecyclerView.Adapter<MealTypeListAdapter.MealTypeListViewHolder>() {

    inner class MealTypeListViewHolder(val binding: CardLayoutOfMealsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: MealModel){
            binding.apply {
                foodName.text = data.foodName
                proteinValue.text = String.format("%.1f gm",data.proteins)
                carbsValue.text = String.format("%.1f carbs",data.carbohydrates)
                fatValue.text = String.format("%.1f gm",data.fats)
                caloriesValue.text = String.format("%.1f cal",data.calories)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealTypeListViewHolder {
        val binding = CardLayoutOfMealsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MealTypeListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MealTypeListViewHolder, position: Int) {
        holder.bind(data[position])
    }
}