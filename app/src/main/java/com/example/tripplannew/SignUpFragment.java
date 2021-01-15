package com.example.tripplannew;

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

import com.example.tripplannew.data.local.Account;
import com.example.tripplannew.viewmodels.LoginViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpFragment extends Fragment {

    private LoginViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);

        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btnSignUp = getActivity().findViewById(R.id.btnSignUpDone);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(v);
            }
        });
    }

    private void signUp(View view)
    {
        String username = ((TextInputLayout)getActivity().findViewById(R.id.signUpUsername)).getEditText().getText().toString();
        String password = ((TextInputLayout)getActivity().findViewById(R.id.signUpPassword)).getEditText().getText().toString();
        String name = ((TextInputLayout)getActivity().findViewById(R.id.signUpName)).getEditText().getText().toString();

        mViewModel.signup(username, password, name).observe(this, isSucceed -> {
            if(isSucceed == null)
            {
                Toast toast = Toast.makeText(getActivity(), "Lỗi đường truyền", Toast.LENGTH_LONG);
                toast.show();
            }
            else if(isSucceed)
            {
                Toast toast = Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_LONG);
                toast.show();
                Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_loginFragment);
            }
            else
            {
                Toast toast = Toast.makeText(getActivity(), "Đăng ký thất bại", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
