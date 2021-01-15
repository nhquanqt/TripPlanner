package com.example.tripplannew;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tripplannew.adapters.TripArrayAdapter;
import com.example.tripplannew.data.local.Trip;
import com.example.tripplannew.viewmodels.ExpenseListViewModel;
import com.example.tripplannew.viewmodels.SettingViewModel;
import com.example.tripplannew.viewmodels.TripInfoViewModel;
import com.example.tripplannew.viewmodels.TripListViewModel;

import java.util.ArrayList;

public class ListTripFragment extends Fragment {

    private TripListViewModel mTripListViewModel;
    private ExpenseListViewModel mExpenseListViewModel;
    private TripInfoViewModel mTripInfoViewModel;
    private SettingViewModel mSettingViewModel;

    private ListView mListView;
    private Button mBtnAddTrip;
    private Button mBtnSetting;

    private ArrayList<Trip> mTripArray;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mTripListViewModel = new ViewModelProvider(getActivity()).get(TripListViewModel.class);
        mExpenseListViewModel = new ViewModelProvider(getActivity()).get(ExpenseListViewModel.class);
        mTripInfoViewModel = new ViewModelProvider(getActivity()).get(TripInfoViewModel.class);
        mSettingViewModel = new ViewModelProvider(getActivity()).get(SettingViewModel.class);

        return inflater.inflate(R.layout.fragment_list_trip, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView = getActivity().findViewById(R.id.listViewTrip);

        mTripListViewModel.getAllTrips().observe(getActivity(), trips -> {
            Activity activity = getActivity();
            if(activity != null)
            {
                mTripArray = new ArrayList<>(trips);
                TripArrayAdapter adapter = new TripArrayAdapter(getActivity(), mTripArray);
                mListView.setAdapter(adapter);
            }
        });

        mBtnAddTrip = getActivity().findViewById(R.id.btnTripAdd);
        mBtnAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_listTripFragment_to_addTripFragment);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mTripArray != null)
                {
                    Trip trip = mTripArray.get(position);
                    mExpenseListViewModel.setTripId(trip.getId());
                    mExpenseListViewModel.setTrip(trip);
                    Navigation.findNavController(view).navigate(R.id.action_listTripFragment_to_listExpenseFragment);
                }
            }
        });

        mBtnSetting = getActivity().findViewById(R.id.btnSetting);
        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_listTripFragment_to_settingFragment);
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(mTripArray != null)
                {
                    Trip trip = mTripArray.get(position);
//                    mTripListViewModel.deleteTrip(trip);
                    mTripInfoViewModel.setTrip(trip);
                    Navigation.findNavController(view).navigate(R.id.action_listTripFragment_to_infoTripFragment);
                }
                return false;
            }
        });
    }
}
