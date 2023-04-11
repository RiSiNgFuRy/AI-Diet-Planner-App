package com.example.aidietplanner_v1.Java.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.aidietplanner_v1.Java.Models.UserLoginModel;
import com.example.aidietplanner_v1.Java.Models.UserLoginResponseModel;
import com.example.aidietplanner_v1.Kotlin.Activities.HomeActivity;
import com.example.aidietplanner_v1.Kotlin.Utils.SharedPrefs;
import com.example.aidietplanner_v1.R;
import com.example.aidietplanner_v1.Services.RetrofitClient;
import com.example.aidietplanner_v1.databinding.FragmentLoginBinding;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    View view;
    private FragmentLoginBinding binding;
    private String email,pass;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null)
            binding.userMail.setText(getArguments().getString("emailAddress"));

        binding.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                email = binding.userMail.getText().toString();
                pass = binding.userPass.getText().toString();
                if(email.isEmpty() || email.isEmpty())
                    Toast.makeText(getContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                else if(validateMail(String.valueOf(email))) {
                    binding.signInBtn.setEnabled(false);
                    validateUser(email, pass);
                }
            }
        });

        binding.newUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main_frag_frame,new SignupFragment()).commit();
            }
        });
    }

    public boolean validateMail(String email){
        if(email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
            return true;
        binding.userMail.setError("Invalid email address");
        return false;
    }

    public void validateUser(String email, String password){
        try {

            RetrofitClient.INSTANCE.buildService()
                    .verifyLoginCredentials(new UserLoginModel(email, password)).enqueue(new Callback<UserLoginResponseModel>() {
                        @Override
                        public void onResponse(Call<UserLoginResponseModel> call, Response<UserLoginResponseModel> response) {
                            if(response.isSuccessful()){
                                UserLoginResponseModel responseModel = response.body();

                                assert responseModel != null;
                                new SharedPrefs(getActivity(), getString(R.string.shared_pref_key))
                                        .setUserCredentials(
                                                responseModel.getToken(),
                                                responseModel.getUsername(),
                                                responseModel.getUserId(),
                                                responseModel.getEmail(),
                                                responseModel.getHeight(),
                                                responseModel.getWeight(),
                                                responseModel.getGenderId(),
                                                responseModel.getAge(),
                                                responseModel.getGoalId(),
                                                responseModel.getFoodPreferenceId()
                                        );

                                startActivity(new Intent(getActivity(), HomeActivity.class));
                                requireActivity().finish();
                                Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(), getString(R.string.server_error_occurred), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserLoginResponseModel> call, Throwable t) {
                            Toast.makeText(getActivity(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                        }
                    });
        }catch (Exception e){
            Toast.makeText(getActivity(), getString(R.string.not_able_to_connect_to_server), Toast.LENGTH_SHORT).show();
        }
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        View currentFocusedView = getActivity().getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}