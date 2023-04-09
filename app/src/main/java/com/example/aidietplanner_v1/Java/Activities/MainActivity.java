package com.example.aidietplanner_v1.Java.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aidietplanner_v1.Java.Fragments.LoginFragment;
import com.example.aidietplanner_v1.Java.Fragments.MainFragment;
import com.example.aidietplanner_v1.Java.Fragments.SplashScreen;
import com.example.aidietplanner_v1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new SplashScreen()).commit();
    }
}