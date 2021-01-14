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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tripplannew.data.Account;
import com.example.tripplannew.viewmodels.LoginViewModel;
import com.example.tripplannew.viewmodels.SettingViewModel;
import com.example.tripplannew.viewmodels.TripListViewModel;

import java.util.Calendar;

public class SettingFragment extends Fragment {
    private Button mBtnLogOut;
    private TextView mBtnBack;
    private EditText mShowName;
    private Button mEtDateOfBirth;
    private EditText mEtPlaceOfBirth;
    private Button mBtnSave;

    private TripListViewModel mTripListViewModel;
    private SettingViewModel mSettingViewModel;

    private Account mAccount;

    private DatePickerDialog.OnDateSetListener tvDateOfBirthSetListener;
    private String mDateOfBirth;

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

        mShowName = getActivity().findViewById(R.id.show_name);

        mEtDateOfBirth = getActivity().findViewById(R.id.etDateOfBirth);
        mEtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar(tvDateOfBirthSetListener);
            }
        });

        tvDateOfBirthSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month= month+1;
                mDateOfBirth = day+"/"+month+"/"+year;
                mEtDateOfBirth.setText(mDateOfBirth);
            }
        };


        mEtPlaceOfBirth = getActivity().findViewById(R.id.etPlaceOfBirth);
        mSettingViewModel.getAccount(mTripListViewModel.getUserId()).observe(
                getActivity(), account -> {
                    mAccount = account;
                    mShowName.setText(account.getName());
                    if(account.getDateOfBirth() != null)
                    {
                        mEtDateOfBirth.setText(account.getDateOfBirth());
                    }
                    if(account.getPlaceOfBirth() != null)
                    {
                        mEtPlaceOfBirth.setText(account.getPlaceOfBirth());
                    }
                });

        mBtnSave = getActivity().findViewById(R.id.btnSave);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mShowName.getText().toString();
                String dateOfBirth = mEtDateOfBirth.getText().toString();
                String placeOfBirth = mEtPlaceOfBirth.getText().toString();
                mAccount.update(name, dateOfBirth, placeOfBirth);

                mSettingViewModel.updateAccount(mAccount);

                Navigation.findNavController(v).navigate(R.id.action_settingFragment_to_listTripFragment);
            }
        });
    }

    private void showCalendar(DatePickerDialog.OnDateSetListener tv){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                tv,year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
