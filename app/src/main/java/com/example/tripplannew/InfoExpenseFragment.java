package com.example.tripplannew;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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

import com.example.tripplannew.data.webservice.Expense;
import com.example.tripplannew.viewmodels.ExpenseInfoViewModel;
import com.example.tripplannew.viewmodels.ExpenseListViewModel;
import com.example.tripplannew.viewmodels.MapViewModel;

import java.util.Calendar;

public class InfoExpenseFragment extends Fragment {

    private ExpenseListViewModel mExpenseListViewModel;
    private ExpenseInfoViewModel mExpenseInfoViewModel;
    private MapViewModel mMapViewModel;

    private EditText mEtCost;
    private Spinner mStype;
    private Button mBtnSubmitExpense;
    private Button mBtnBack;
    private Button mBtnPlace;
    private Button mBtnDate;
    private Button mBtnDelete;

    private Expense mExpense;

    private DatePickerDialog.OnDateSetListener mOnDateSetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("check", "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);

        mExpenseListViewModel = new ViewModelProvider(getActivity()).get(ExpenseListViewModel.class);
        mMapViewModel = new ViewModelProvider(getActivity()).get(MapViewModel.class);
        mExpenseInfoViewModel = new ViewModelProvider(getActivity()).get(ExpenseInfoViewModel.class);

        return inflater.inflate(R.layout.fragment_info_expense, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i("check", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        mEtCost = getActivity().findViewById(R.id.item_cost);
        mStype = getActivity().findViewById(R.id.item_type);
        mBtnDate = getActivity().findViewById(R.id.item_date);
        mBtnPlace = getActivity().findViewById(R.id.item_place);

        mBtnSubmitExpense = getActivity().findViewById(R.id.btnExpenseSubmit);
        mBtnBack = getActivity().findViewById(R.id.btnBack);
        mBtnDelete = getActivity().findViewById(R.id.btnDelete);

        mExpenseInfoViewModel.getExpense().observe(getActivity(),expense -> {
            mExpense = expense;
            mEtCost.setText(String.valueOf((int)expense.getCost()));
            mStype.setSelection(expense.getType());
            mBtnDate.setText(expense.getDate());
            mBtnPlace.setText(expense.getPlace());
        });
        mBtnSubmitExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateExpense();
                Navigation.findNavController(v).navigate(R.id.action_infoExpenseFragment_to_listExpenseFragment);
            }
        });
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExpenseListViewModel.deleteExpense(mExpense).observe(getActivity(), status -> {
                    Navigation.findNavController(view).navigate(R.id.action_infoExpenseFragment_to_listExpenseFragment);
                });
            }
        });


        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_infoExpenseFragment_to_listExpenseFragment);
            }
        });


        mBtnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMapViewModel.setBackActionId(R.id.action_mapFragment_to_addExpenseFragment);
                Navigation.findNavController(v).navigate(R.id.action_addExpenseFragment_to_mapFragment);
            }
        });




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

    private void updateExpense()
    {
        int cost = 0;
        int type = ((Spinner)getActivity().findViewById(R.id.item_type)).getSelectedItemPosition();
        if ((String.valueOf(((EditText)getActivity().findViewById(R.id.item_cost)).getText())).length() != 0)
            cost = Integer.parseInt((String.valueOf(((EditText)getActivity().findViewById(R.id.item_cost)).getText())));
        String date = ((Button)getActivity().findViewById(R.id.item_date)).getText().toString();
        String place = ((Button)getActivity().findViewById(R.id.item_place)).getText().toString();
        mExpense.update("", cost, type, date,place);
        mExpenseListViewModel.updateExpense(mExpense);
//        mExpenseListViewModel.addExpense(new Expense(mExpenseListViewModel.getTripId(), "", cost, type, item_type));

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
