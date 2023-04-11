package com.example.aidietplanner_v1.Kotlin.Binders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Adapter.GenericAdapter
import com.example.aidietplanner_v1.Kotlin.Models.BMIResultModel
import com.example.aidietplanner_v1.databinding.CardLayoutBmiResultBinding

class BMIResultBinder(val adapter: GenericAdapter, val activity: FragmentActivity): DataBinder<BMIResultBinder.BMIResultViewHolder>() {

    inner class BMIResultViewHolder(val binding: CardLayoutBmiResultBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: BMIResultModel){
            binding.apply {
                bmiValue.text = data.bmiValue
                bmiResult.text = data.bmiResultTxt
            }
        }
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardLayoutBmiResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BMIResultViewHolder(binding)
    }

    override fun bindViewHolder(holder: BMIResultViewHolder, position: Int) {
        val data = adapter.getData(position) as BMIResultModel
        holder.bind(data)
    }
}