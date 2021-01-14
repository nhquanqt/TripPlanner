package com.example.tripplannew;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tripplannew.data.Account;
import com.example.tripplannew.viewmodels.LoginViewModel;
import com.example.tripplannew.viewmodels.TripListViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {

    private LoginViewModel mLoginViewModel;
    private TripListViewModel mTripListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mLoginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        mTripListViewModel = new ViewModelProvider(getActivity()).get(TripListViewModel.class);

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button btnSignUp = getActivity().findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });

        Button btnLogin = getActivity().findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }

    private void login(View view)
    {
        String username = ((TextInputLayout)getActivity().findViewById(R.id.loginUsername)).getEditText().getText().toString();
        String password = ((TextInputLayout)getActivity().findViewById(R.id.loginPassword)).getEditText().getText().toString();

        mLoginViewModel.getAccount(username, password).observe(this, accounts -> {
            if(accounts.size() > 0)
            {
                Account account = accounts.get(0);
                mTripListViewModel.setUserId(account.getId());

                Toast toast = Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_LONG);
                toast.show();

                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_listTripFragment);
            }
            else
            {
                Toast toast = Toast.makeText(getActivity(), "Đăng nhập thất bại", Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }
}
