package com.example.aidietplanner_v1.Kotlin.Binders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Adapter.GenericAdapter
import com.example.aidietplanner_v1.Kotlin.Adapter.GoalOptionsAdapter
import com.example.aidietplanner_v1.Kotlin.Models.UserGoalSelectionModel
import com.example.aidietplanner_v1.databinding.CardLayoutSettingsListBinding

class UserGoalSelectionBinder(val adapter: GenericAdapter, val activity: FragmentActivity): DataBinder<UserGoalSelectionBinder.UserGoalSelectionViewHolder>() {

    inner class UserGoalSelectionViewHolder(val binding: CardLayoutSettingsListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: UserGoalSelectionModel){
            binding.apply {
                cardHeading.text = data.heading
                cardList.apply {
                    layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
                    adapter = GoalOptionsAdapter(activity, data.goalsList)
                }
            }
        }
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardLayoutSettingsListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserGoalSelectionViewHolder(binding)
    }

    override fun bindViewHolder(holder: UserGoalSelectionViewHolder, position: Int) {
        val data = adapter.getData(position) as UserGoalSelectionModel
        holder.bind(data)
    }
}