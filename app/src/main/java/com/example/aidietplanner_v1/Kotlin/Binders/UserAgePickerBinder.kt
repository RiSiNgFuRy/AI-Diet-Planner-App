package com.example.aidietplanner_v1.Kotlin.Binders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Adapter.GenericAdapter
import com.example.aidietplanner_v1.Kotlin.Models.UserAgePickerModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.databinding.CardLayoutUserAgePickerBinding

class UserAgePickerBinder(val adapter: GenericAdapter,val activity: FragmentActivity): DataBinder<UserAgePickerBinder.UserAgePickerViewHolder>() {

    inner class UserAgePickerViewHolder(val binding: CardLayoutUserAgePickerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: UserAgePickerModel){
            binding.apply{
                cardHeading.text = data.heading
                agePickerSeekBar.progress = data.age.toInt()
                agePickerSeekBar.setOnSeekBarChangeListener(object: OnSeekBarChangeListener{
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        ageValue.text = progress.toString()
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {

                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {

                    }

                })
            }
        }
    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardLayoutUserAgePickerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserAgePickerViewHolder(binding)
    }

    override fun bindViewHolder(holder: UserAgePickerViewHolder, position: Int) {
        val data = adapter.getData(position) as UserAgePickerModel
        holder.bind(data)
    }
}