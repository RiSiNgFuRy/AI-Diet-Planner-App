package com.example.aidietplanner_v1.Java.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aidietplanner_v1.Java.Models.LoginTokenVerificationModel;
import com.example.aidietplanner_v1.Java.Models.TokenStatus;
import com.example.aidietplanner_v1.Kotlin.Activities.HomeActivity;
import com.example.aidietplanner_v1.Kotlin.Utils.SharedPrefs;
import com.example.aidietplanner_v1.R;
import com.example.aidietplanner_v1.Services.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends Fragment {

    public SplashScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                verifyToken();
            }
        },1500);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    public void verifyToken(){
        String token = new SharedPrefs(getActivity(), getString(R.string.shared_pref_key)).getToken();
        if(token!=null) {
            try {
                RetrofitClient.INSTANCE.buildService()
                        .verifyToken(new LoginTokenVerificationModel(token)).enqueue(new Callback<TokenStatus>() {
                            @Override
                            public void onResponse(Call<TokenStatus> call, Response<TokenStatus> response) {
                                if (response.isSuccessful()) {
                                    startActivity(new Intent(getActivity(), HomeActivity.class));
                                    getActivity().finish();
                                    Toast.makeText(getActivity(), "Token Verified", Toast.LENGTH_SHORT).show();
                                } else {
                                    getActivity()
                                            .getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.main_frame, new MainFragment())
                                            .commit();
                                    Toast.makeText(getActivity(), "Token not valid or expired", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<TokenStatus> call, Throwable t) {
                                Toast.makeText(getActivity(), getString(R.string.server_error_occurred), Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), getString(R.string.not_able_to_connect_to_server), Toast.LENGTH_SHORT).show();
            }
        }else{
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, new MainFragment())
                    .commit();
        }
    }
}