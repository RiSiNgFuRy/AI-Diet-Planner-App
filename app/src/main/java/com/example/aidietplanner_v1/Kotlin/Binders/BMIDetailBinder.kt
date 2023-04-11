package com.example.aidietplanner_v1.Kotlin.Binders

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.aidietplanner_v1.Kotlin.Adapter.GenericAdapter
import com.example.aidietplanner_v1.Kotlin.Models.BMIDetailsModel
import com.example.aidietplanner_v1.Kotlin.Models.BmiDetailsRequestModel
import com.example.aidietplanner_v1.Kotlin.Utils.SharedPrefs
import com.example.aidietplanner_v1.Kotlin.ViewModel.SettingsViewModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.databinding.CardLayoutBmiDetailsBinding

class BMIDetailBinder(val activity: FragmentActivity, private val adapter: GenericAdapter): DataBinder<BMIDetailBinder.BMIDetailViewHolder>() {

    lateinit var settingsViewModel: SettingsViewModel
    lateinit var sharedPrefs: SharedPrefs
    inner class BMIDetailViewHolder(private val binding: CardLayoutBmiDetailsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: BMIDetailsModel){
            binding.apply {
                userHeight.text = sharedPrefs.getUserHeight() + "cm"
                userWeight.text = sharedPrefs.getUserWeight() + "kg"
                itemView.setOnClickListener {
                    showBmiInputDialogBox(BMIDetailsModel(sharedPrefs.getUserHeight().toString(), sharedPrefs.getUserWeight().toString()))
                }
            }
        }

    }

    override fun newViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        settingsViewModel = ViewModelProvider(activity)[SettingsViewModel::class.java]
        sharedPrefs = SharedPrefs(activity, activity.getString(R.string.shared_pref_key))
        val binding = CardLayoutBmiDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BMIDetailViewHolder(binding)
    }

    override fun bindViewHolder(holder: BMIDetailViewHolder, position: Int) {
        val data = adapter.getData(position) as BMIDetailsModel
        holder.bind(data)
    }

    private fun showBmiInputDialogBox(data: BMIDetailsModel){
        val dialogBox = Dialog(activity)
        dialogBox.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogBox.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        dialogBox.setContentView(R.layout.dialog_box_height_and_weight_input)
        dialogBox.window?.setGravity(Gravity.CENTER)
        val heightPicker = dialogBox.findViewById<SeekBar>(R.id.heightPicker)
        val heightValue = dialogBox.findViewById<TextView>(R.id.heightValue)
        val weightPicker = dialogBox.findViewById<SeekBar>(R.id.weightPicker)
        val weightValue = dialogBox.findViewById<TextView>(R.id.weightValue)
        val doneBtn = dialogBox.findViewById<TextView>(R.id.doneBtn)

        heightValue.text = data.height
        weightValue.text = data.weight
        heightPicker.progress = data.height.toInt()
        weightPicker.progress = data.weight.toInt()

        heightPicker.setOnSeekBarChangeListener(object: OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                heightValue.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
            }
        })

        weightPicker.setOnSeekBarChangeListener(object: OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                weightValue.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                TODO("Not yet implemented")
            }

        })

        doneBtn.setOnClickListener {
            settingsViewModel.setBmiDetails(
                sharedPrefs.getUserId(),
                BmiDetailsRequestModel(
                    Integer.parseInt(heightValue.text as String),
                    Integer.parseInt(weightValue.text as String)
                )
            )
            dialogBox.dismiss()
        }
        dialogBox.show()
    }
}