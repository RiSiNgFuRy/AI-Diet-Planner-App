package com.example.aidietplanner_v1.Kotlin.Binders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Adapter.GenericAdapter
import com.example.aidietplanner_v1.Kotlin.Models.BMIResultModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.databinding.CardLayoutBmiResultBinding

class BMIResultBinder(val adapter: GenericAdapter, val activity: FragmentActivity): DataBinder<BMIResultBinder.BMIResultViewHolder>() {

    inner class BMIResultViewHolder(val binding: CardLayoutBmiResultBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: BMIResultModel){
            binding.apply {
                data.let {
                    heightValue.text = it.height.plus(activity.getString(R.string.height_unit))
                    weightValue.text = it.weight.plus(activity.getString(R.string.weight_unit))
                    bmiResultImg.setImageResource(it.imgDrawableId)
                    bmiValueCard.apply {
                        heading.text = "BMI"
                        headingValue.text = it.bmiValue
                        headingUnit.text = activity.getString(R.string.bmi_unit)
                    }
                    bmrValueCard.apply {
                        heading.text = "BMR"
                        headingValue.text = it.bmrValue
                        headingUnit.text = activity.getString(R.string.bmr_unit)
                    }
                }
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