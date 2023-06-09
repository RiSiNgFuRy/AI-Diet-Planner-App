package com.example.aidietplanner_v1.Kotlin.Binders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Adapter.GenericAdapter
import com.example.aidietplanner_v1.Kotlin.Helper.ImageHelper
import com.example.aidietplanner_v1.Kotlin.Models.AllergiesOptionsModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.databinding.CardLayoutAllergiesOptionBinding

class SettingListAllergiesSelectionBinder(val activity: FragmentActivity, val adapter: GenericAdapter): DataBinder<SettingListAllergiesSelectionBinder.SettingListAllergiesViewHolder>() {

    val selectedItems = mutableListOf<Int>()
    inner class SettingListAllergiesViewHolder(val binding: CardLayoutAllergiesOptionBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: AllergiesOptionsModel, position: Int){
            binding.apply {
                allergyTypeName.text = data.heading
                ImageHelper.loadImage(activity, allergyTypeImg, data.img)
                if (selectedItems.contains(position)){
                    itemCard.setBackgroundColor(activity.resources.getColor(R.color.primaryColor))
                    allergyTypeName.setTextColor(activity.resources.getColor(R.color.white))
                }else{
                    itemCard.setBackgroundColor(activity.resources.getColor(R.color.e3e3e3))
                    allergyTypeName.setTextColor(activity.resources.getColor(R.color.black))
                }
                setOnClickListeners(binding, position)
            }
        }
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardLayoutAllergiesOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingListAllergiesViewHolder(binding)
    }

    override fun bindViewHolder(holder: SettingListAllergiesViewHolder, position: Int) {
        val data = adapter.getData(position) as AllergiesOptionsModel
        holder.bind(data, position)
    }

    private fun setOnClickListeners(binding: CardLayoutAllergiesOptionBinding, position: Int){
        binding.itemCard.setOnClickListener{
            if (selectedItems.contains(position))
                selectedItems.remove(position)
            else
                selectedItems.add(position)
            adapter.dataChanged(position)
        }
    }

}