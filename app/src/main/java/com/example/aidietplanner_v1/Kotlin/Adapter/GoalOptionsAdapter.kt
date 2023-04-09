package com.example.aidietplanner_v1.Kotlin.Adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Models.GoalOptionsModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.databinding.CardLayoutAllergiesOptionBinding
import com.example.aidietplanner_v1.databinding.CardLayoutGenderOptionsBinding
import com.example.aidietplanner_v1.databinding.CardLayoutOfUserGoalBinding

class GoalOptionsAdapter(val activity: FragmentActivity,val data: ArrayList<GoalOptionsModel>): RecyclerView.Adapter<GoalOptionsAdapter.GoalOptionsViewHolder>() {

    var selectedItem = -1

    inner class GoalOptionsViewHolder(val binding: CardLayoutOfUserGoalBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: GoalOptionsModel){
            binding.apply {
                goalType.text = data.type
                if (selectedItem == adapterPosition) {
                    goalType.background = activity.getDrawable(R.drawable.curved_corner_card)
                    goalType.setTextColor(activity.resources.getColor( R.color.white))
                } else {
                    goalType.background = null
                    goalType.setTextColor(activity.resources.getColor( R.color.black))
                }
            }
            setOnClickListeners(binding, adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalOptionsViewHolder {
        val binding = CardLayoutOfUserGoalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GoalOptionsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: GoalOptionsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    private fun setOnClickListeners(binding: CardLayoutOfUserGoalBinding, position: Int){
        binding.goalType.setOnClickListener {
            var prev = selectedItem
            selectedItem = position
            notifyItemChanged(selectedItem)
            notifyItemChanged(prev)
        }
    }
}