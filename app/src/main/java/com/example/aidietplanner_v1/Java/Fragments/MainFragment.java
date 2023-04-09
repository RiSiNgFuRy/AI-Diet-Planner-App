package com.example.aidietplanner_v1.Java.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aidietplanner_v1.Kotlin.Activities.HomeActivity;
import com.example.aidietplanner_v1.R;

public class MainFragment extends Fragment {

    View v;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frag_frame,new LoginFragment()).commit();
        return v;
    }
}