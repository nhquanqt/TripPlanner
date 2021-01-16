package com.example.tripplannew;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tripplannew.data.webservice.Account;
import com.example.tripplannew.viewmodels.SettingViewModel;
import com.example.tripplannew.viewmodels.TripListViewModel;

import java.util.Calendar;

public class SettingFragment extends Fragment {
    private Button mBtnLogOut;
    private TextView mBtnBack;


    private TripListViewModel mTripListViewModel;
    private SettingViewModel mSettingViewModel;

    RelativeLayout mRLInfo;
    RelativeLayout mRLTool;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mTripListViewModel = new ViewModelProvider(getActivity()).get(TripListViewModel.class);
        mSettingViewModel = new ViewModelProvider(getActivity()).get(SettingViewModel.class);

        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBtnLogOut = getActivity().findViewById(R.id.btnLogOut);
        mBtnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_loginFragment);
            }
        });
        mBtnBack=getActivity().findViewById(R.id.btnBack);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_listTripFragment);
            }
        });

        mRLInfo=getActivity().findViewById(R.id.rl_info);
        mRLInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_infoUserFragment);
            }
        });

        mRLTool = getActivity().findViewById(R.id.rl_tools);
        mRLTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_settingFragment_to_toolsFragment);
            }
        });
    }


}
