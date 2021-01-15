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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tripplannew.data.local.Trip;
import com.example.tripplannew.viewmodels.MapViewModel;
import com.example.tripplannew.viewmodels.TripInfoViewModel;
import com.example.tripplannew.viewmodels.TripListViewModel;

import java.util.Calendar;

public class InfoTripFragment extends Fragment {

    private TripListViewModel mTripListViewModel;
    private TripInfoViewModel mTripInfoViewModel;
    private MapViewModel mMapViewModel;

    private Button mBtnBack;
    private EditText mEtTrip;
    private EditText mEtBudget;
    private Button mBtnStartDate;
    private Button mBtnEndDate;
    private Button mBtnDeparture;
    private Button mBtnTripSubmit;
    private Button mBtnDelete;

    private Trip mTrip;

    private DatePickerDialog.OnDateSetListener mBtnStartSetListener, mBtnEndSetListener;
    private String mStartDate, mEndDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_info_trip, container, false);

        mTripListViewModel = new ViewModelProvider(getActivity()).get(TripListViewModel.class);
        mTripInfoViewModel = new ViewModelProvider(getActivity()).get(TripInfoViewModel.class);
        mMapViewModel = new ViewModelProvider(getActivity()).get(MapViewModel.class);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mEtTrip = getView().findViewById(R.id.etTrip);
        mEtBudget = getView().findViewById(R.id.etBudget);
        mBtnStartDate = getView().findViewById(R.id.tvStartDate);
        mBtnEndDate = getView().findViewById(R.id.tvEndDate);
        mBtnDeparture = getView().findViewById(R.id.tvDeparture);
        mBtnTripSubmit = getView().findViewById(R.id.btnTripSubmit);
        mBtnBack = getActivity().findViewById(R.id.btnBack);
        mBtnDelete = getActivity().findViewById(R.id.btnDelete);

        mTripInfoViewModel.getTrip().observe(getActivity(), trip -> {
            mTrip = trip;
            mEtTrip.setText(trip.getTripName());
            mEtBudget.setText(String.valueOf((int)trip.getBudget()));
            mBtnStartDate.setText(trip.getStartDate());
            mBtnEndDate.setText(trip.getEndDate());
            mBtnDeparture.setText(trip.getDeparture());
        });

        mBtnStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar(mBtnStartSetListener);
            }
        });
        mBtnStartSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month= month+1;
                mStartDate = day+"/"+month+"/"+year;
                mBtnStartDate.setText(mStartDate);
            }
        };

        mBtnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar(mBtnEndSetListener);
            }
        });
        mBtnEndSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month= month+1;
                mEndDate = day+"/"+month+"/"+year;
                mBtnEndDate.setText(mEndDate);
            }
        };

        mBtnDeparture = getActivity().findViewById(R.id.tvDeparture);
        mBtnDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMapViewModel.setBackActionId(R.id.action_mapFragment_to_infoTripFragment);
                Navigation.findNavController(v).navigate(R.id.action_infoTripFragment_to_mapFragment);
            }
        });

        mMapViewModel.getLocation().observe(getActivity(), location -> {
            if(location != null && location.length() > 0)
            {
                mBtnDeparture.setText(location);
            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_infoTripFragment_to_listTripFragment);
            }
        });

        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTripListViewModel.deleteTrip(mTrip);
                Navigation.findNavController(v).navigate(R.id.action_infoTripFragment_to_listTripFragment);
            }
        });

        mBtnTripSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tripName = ((EditText)getActivity().findViewById(R.id.etTrip)).getText().toString();
                String stringBudget = ((EditText)getActivity().findViewById(R.id.etBudget)).getText().toString();
                String startDate = ((Button)getActivity().findViewById(R.id.tvStartDate)).getText().toString();
                String endDate = ((Button)getActivity().findViewById(R.id.tvEndDate)).getText().toString();
                String departure = ((Button)getActivity().findViewById(R.id.tvDeparture)).getText().toString();

                if(stringBudget.length() == 0) stringBudget = "0";
                float budget = Float.parseFloat(stringBudget);

                mTrip.update(tripName, budget, startDate, endDate, departure);
                mTripListViewModel.updateTrip(mTrip);
                Navigation.findNavController(v).navigate(R.id.action_infoTripFragment_to_listTripFragment);
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
