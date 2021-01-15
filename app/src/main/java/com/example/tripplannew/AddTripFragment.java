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

import com.example.tripplannew.data.webservice.Trip;
import com.example.tripplannew.viewmodels.MapViewModel;
import com.example.tripplannew.viewmodels.TripListViewModel;

import java.util.Calendar;

public class AddTripFragment extends Fragment {

    private TripListViewModel mTripListViewModel;
    private MapViewModel mMapViewModel;

    private Button mBtnSubmitTrip, mBtnCancelTrip;
    private TextView mTvStartDate, mTvEndDate, mTvDeparture;

    private DatePickerDialog.OnDateSetListener tvStartSetListener, tvEndSetListener;
    private String mStartDate, mEndDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mTripListViewModel = new ViewModelProvider(getActivity()).get(TripListViewModel.class);
        mMapViewModel = new ViewModelProvider(getActivity()).get(MapViewModel.class);

        return inflater.inflate(R.layout.fragment_add_trip, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBtnSubmitTrip = getActivity().findViewById(R.id.btnTripSubmit);
        mBtnSubmitTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTrip(v);
            }
        });

        mBtnCancelTrip = getActivity().findViewById(R.id.btnTripCancel);
        mBtnCancelTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_addTripFragment_to_listTripFragment);
            }
        });

        mTvStartDate = (TextView)getActivity().findViewById(R.id.tvStartDate);
        mTvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar(tvStartSetListener);
            }
        });

        mTvEndDate =(TextView)getActivity().findViewById(R.id.tvEndDate);
        mTvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar(tvEndSetListener);
            }
        });

        mTvDeparture =(TextView)getActivity().findViewById(R.id.tvDeparture);
        mTvDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMapViewModel.setBackActionId(R.id.action_mapFragment_to_addTripFragment);
                Navigation.findNavController(v).navigate(R.id.action_addTripFragment_to_mapFragment);
            }
        });

        tvStartSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month= month+1;
                mStartDate = day+"/"+month+"/"+year;
                mTvStartDate.setText(mStartDate);
            }
        };
        tvEndSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month= month+1;
                mEndDate = day+ "/"+ month+"/"+year;
                mTvEndDate.setText(mEndDate);
            }
        };

        mMapViewModel.getLocation().observe(getActivity(), location -> {
            if(location != null && location.length() > 0)
            {
                mTvDeparture.setText(location);
            }
        });
    }

    private void addTrip(View v)
    {
        String tripName = ((EditText)getActivity().findViewById(R.id.etTrip)).getText().toString();
        String stringBudget = ((EditText)getActivity().findViewById(R.id.etBudget)).getText().toString();
        String startDate = ((Button)getActivity().findViewById(R.id.tvStartDate)).getText().toString();
        String endDate = ((Button)getActivity().findViewById(R.id.tvEndDate)).getText().toString();
        String departure = ((Button)getActivity().findViewById(R.id.tvDeparture)).getText().toString();


        if(stringBudget.length() == 0) stringBudget = "0";
        float budget = Float.parseFloat(stringBudget);
//        mTripListViewModel.insert(new Trip(mTripListViewModel.getUserId(), tripName, budget, startDate, endDate, departure));
        mTripListViewModel.addTrip(new Trip(mTripListViewModel.getUserId(), tripName, budget, startDate, endDate, departure)).observe(getActivity(), status -> {
            Navigation.findNavController(v).navigate(R.id.action_addTripFragment_to_listTripFragment);
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
