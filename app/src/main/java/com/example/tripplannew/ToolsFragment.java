package com.example.tripplannew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tripplannew.viewmodels.PlaceOfInterestViewModel;

public class ToolsFragment extends Fragment {

    RelativeLayout mBtnFindATM;
    RelativeLayout mBtnFindRestaurant;

    TextView mBtnBack;

    PlaceOfInterestViewModel mPlaceOfInterestViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_tools, container, false);

        mPlaceOfInterestViewModel = new ViewModelProvider(getActivity()).get(PlaceOfInterestViewModel.class);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBtnBack = getActivity().findViewById(R.id.btnBack_tools);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_toolsFragment_to_settingFragment);
            }
        });

        mBtnFindATM = getActivity().findViewById(R.id.rl_findATM);
        mBtnFindATM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlaceOfInterestViewModel.setPlaceType("atm");
                Navigation.findNavController(v).navigate(R.id.action_toolsFragment_to_findPlacesFragment);
            }
        });

        mBtnFindRestaurant = getActivity().findViewById(R.id.rl_findRestaurant);
        mBtnFindRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlaceOfInterestViewModel.setPlaceType("restaurant");
                Navigation.findNavController(v).navigate(R.id.action_toolsFragment_to_findPlacesFragment);
            }
        });
    }
}
