package com.example.aidietplanner_v1.Kotlin.Fragments

import android.os.BaseBundle
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aidietplanner_v1.Kotlin.Adapter.DietChartAdapter
import com.example.aidietplanner_v1.Kotlin.Utils.BaseModel
import com.example.aidietplanner_v1.R
import com.example.aidietplanner_v1.databinding.FragmentDietChartBinding

class DietChartFragment : Fragment() {

    private lateinit var binding: FragmentDietChartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDietChartBinding.inflate(inflater, container, false)
        binding.dietChartFragList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = DietChartAdapter(requireActivity(), getData())
        }
        return binding.root
    }

    private fun getData(): ArrayList<BaseModel>{
        return arrayListOf(

        )
    }

}