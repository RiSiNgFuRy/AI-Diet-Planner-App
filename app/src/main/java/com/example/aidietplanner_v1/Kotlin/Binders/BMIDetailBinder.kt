package com.example.aidietplanner_v1.Kotlin.Binders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Adapter.GenericAdapter
import com.example.aidietplanner_v1.Kotlin.Models.BMIDetailsModel
import com.example.aidietplanner_v1.databinding.CardLayoutBmiDetailsBinding

class BMIDetailBinder(val activity: FragmentActivity, private val adapter: GenericAdapter): DataBinder<BMIDetailBinder.BMIDetailViewHolder>() {

    class BMIDetailViewHolder(private val binding: CardLayoutBmiDetailsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: BMIDetailsModel){
            binding.apply {
                userHeight.text = data.height
                userWeight.text = data.weight
            }
        }
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardLayoutBmiDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BMIDetailViewHolder(binding)
    }

    override fun bindViewHolder(holder: BMIDetailViewHolder, position: Int) {
        val data = adapter.getData(position) as BMIDetailsModel
        holder.bind(data)
    }
}