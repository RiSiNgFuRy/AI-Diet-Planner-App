package com.example.aidietplanner_v1.Kotlin.Binders

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Adapter.GenericAdapter
import com.example.aidietplanner_v1.Kotlin.Helper.ImageHelper
import com.example.aidietplanner_v1.Kotlin.Models.GenderOptionsModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.databinding.CardLayoutGenderOptionsBinding

class SettingListGenderSelectionBinder(val activity: FragmentActivity, val adapter: GenericAdapter): DataBinder<SettingListGenderSelectionBinder.SettingListGenderSelectionViewHolder>() {

    var selectedItem = -1
    inner class SettingListGenderSelectionViewHolder(val binding: CardLayoutGenderOptionsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: GenderOptionsModel){
            binding.apply {
                genderName.text = data.type
                ImageHelper.loadImage(activity, genderImg, data.imgurl)
                if (selectedItem == adapterPosition) {
                    genderImg.strokeWidth = 10F
                    genderImg.strokeColor = ColorStateList.valueOf(activity.resources.getColor(R.color.primaryColor))
                    genderName.background = activity.getDrawable(R.drawable.curved_corner_card)
                    genderName.setTextColor(activity.resources.getColor( R.color.white))
                } else {
                    genderImg.strokeWidth = 0F
                    genderImg.strokeColor = null
                    genderName.background = null
                    genderName.setTextColor(activity.resources.getColor( R.color.black))
                }
            }
            setOnClickListeners(binding, adapterPosition)
        }

    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardLayoutGenderOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingListGenderSelectionViewHolder(binding)
    }

    override fun bindViewHolder(holder: SettingListGenderSelectionViewHolder, position: Int) {
        val data = adapter.getData(position) as GenderOptionsModel
        holder.bind(data)
    }

    private fun setOnClickListeners(binding: CardLayoutGenderOptionsBinding, position: Int){
        binding.itemCard.setOnClickListener {
            var prev = selectedItem
            selectedItem = position
            adapter.dataChanged(selectedItem)
            adapter.dataChanged(prev)
        }
    }
}