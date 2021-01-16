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
import com.example.tripplannew.viewmodels.ExpenseListViewModel;
import com.example.tripplannew.viewmodels.MapViewModel;

import java.util.Calendar;

public class AddExpenseFragment extends Fragment {

    private ExpenseListViewModel mExpenseListViewModel;
    private MapViewModel mMapViewModel;

    private Button mBtnSubmitExpense;
    private Button mBtnCancelExpense;
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
                addExpense(v);
            }
        });

        mBtnCancelExpense = getActivity().findViewById(R.id.btnExpenseCancel);
        mBtnCancelExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_addExpenseFragment_to_listExpenseFragment);
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
    }

    private void addExpense(View v)
    {
        int cost = 0;
        int type = ((Spinner)getActivity().findViewById(R.id.item_type)).getSelectedItemPosition();

        if ((String.valueOf(((EditText)getActivity().findViewById(R.id.item_cost)).getText())).length() != 0)
            cost = Integer.parseInt((String.valueOf(((EditText)getActivity().findViewById(R.id.item_cost)).getText())));

        String date = ((Button)getActivity().findViewById(R.id.item_date)).getText().toString();
        String place = ((EditText)getActivity().findViewById(R.id.item_place)).getText().toString();
        Log.i("check", date);
        Expense expense = new Expense(mExpenseListViewModel.getTripId(), "", cost, type, date, place);
        mExpenseListViewModel.addExpense(expense).observe(getActivity(), status -> {
            Navigation.findNavController(v).navigate(R.id.action_addExpenseFragment_to_listExpenseFragment);
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
