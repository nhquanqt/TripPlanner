package com.example.tripplannew;

import android.annotation.SuppressLint;
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
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tripplannew.data.Expense;
import com.example.tripplannew.viewmodels.ExpenseListViewModel;
import com.example.tripplannew.viewmodels.MapViewModel;

import java.util.Calendar;

public class AddExpenseFragment extends Fragment {

    private ExpenseListViewModel mExpenseListViewModel;
    private MapViewModel mMapViewModel;

    private Button mBtnSubmitExpense;
    private Button mBtnCancelExpense;
    private Button mBtnPlace;
    private Button mBtnDate;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mExpenseListViewModel = new ViewModelProvider(getActivity()).get(ExpenseListViewModel.class);
        mMapViewModel = new ViewModelProvider(getActivity()).get(MapViewModel.class);

        return inflater.inflate(R.layout.fragment_add_expense, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mBtnSubmitExpense = getActivity().findViewById(R.id.btnExpenseSubmit);
        mBtnSubmitExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpense();
                Navigation.findNavController(v).navigate(R.id.action_addExpenseFragment_to_listExpenseFragment);
            }
        });

        mBtnCancelExpense = getActivity().findViewById(R.id.btnExpenseCancel);
        mBtnCancelExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_addExpenseFragment_to_listExpenseFragment);
            }
        });

        mBtnPlace = getActivity().findViewById(R.id.item_place);
        mBtnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMapViewModel.setBackActionId(R.id.action_mapFragment_to_addExpenseFragment);
                Navigation.findNavController(v).navigate(R.id.action_addExpenseFragment_to_mapFragment);
            }
        });

        mBtnDate = getActivity().findViewById(R.id.item_date);
        mBtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar(mOnDateSetListener);
            }
        });

        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month= month+1;
                String date = String.format("%d/%d/%d", day, month, year);
                mBtnDate.setText(date);
            }
        };

        mMapViewModel.getLocation().observe(getActivity(), location -> {
            if(location != null && location.length() > 0)
            {
                mBtnPlace.setText(location);
            }
        });
    }

    private void addExpense()
    {
        String item_type = (((Spinner)getActivity().findViewById(R.id.item_type)).getSelectedItem()).toString();
        int type;
        int cost = 0;
        if (item_type.equals("Food"))
            type = R.drawable.food;
        else
            type = R.drawable.drink;
        if ((String.valueOf(((EditText)getActivity().findViewById(R.id.item_cost)).getText())).length() != 0)
            cost = Integer.parseInt((String.valueOf(((EditText)getActivity().findViewById(R.id.item_cost)).getText())));

        mExpenseListViewModel.insert(new Expense(mExpenseListViewModel.getTripId(), "", cost, type, item_type));
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
