package com.example.tripplannew;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tripplannew.adapters.ExpenseArrayAdapter;
import com.example.tripplannew.data.webservice.Expense;
import com.example.tripplannew.viewmodels.ExpenseInfoViewModel;
import com.example.tripplannew.viewmodels.ExpenseListViewModel;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class ListExpenseFragment extends Fragment {

    private ExpenseListViewModel mExpenseListViewModel;
    private ListView mListView;
    private Button mBtnAddExpense;
    private Button mBtnBackToTrips;
    private TextView mTvTripName;
    private PieChart mPieChart;
    private ExpenseInfoViewModel mExpenseInfoViewModel;
    private ArrayList<Expense> mExpenseArray;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mExpenseListViewModel = new ViewModelProvider(getActivity()).get(ExpenseListViewModel.class);
        mExpenseInfoViewModel = new ViewModelProvider(getActivity()).get(ExpenseInfoViewModel.class);
        return inflater.inflate(R.layout.fragment_list_expense, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int budget = (int)mExpenseListViewModel.getTripBudget();
        int totalexpense = 1000000;
        mListView = getActivity().findViewById(R.id.listViewExpense);

        mTvTripName = getActivity().findViewById(R.id.tvTripName);
        mTvTripName.setText(mExpenseListViewModel.getTrip().getTripName());

        TextView trip_budget = (TextView)getActivity().findViewById(R.id.trip_budget);
        trip_budget.setText(String.format("%s VND", String.valueOf(budget)));



        mExpenseListViewModel.getAllExpenses().observe(getActivity(), expenses -> {
            Activity activity = getActivity();
            if(activity != null)
            {
                mExpenseArray = new ArrayList<>(expenses);
                ExpenseArrayAdapter adapter = new ExpenseArrayAdapter(getActivity(), mExpenseArray);
                mListView.setAdapter(adapter);
                setTotalCost(adapter);
            }
        });

        mBtnAddExpense = getActivity().findViewById(R.id.btnExpenseAdd);
        mBtnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_listExpenseFragment_to_addExpenseFragment);
            }
        });

        mPieChart = getActivity().findViewById(R.id.imBieuDo);
        mPieChart.addPieSlice(
                new PieModel(
                        "Ngân sách",
                        budget,
                        Color.parseColor("#FFFFFF")));
        mPieChart.addPieSlice(
                new PieModel(
                        "Chi tiêu",
                        totalexpense,
                        Color.parseColor("#DC7633")));
        mPieChart.startAnimation();


        mPieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_listExpenseFragment_to_pieChartFragment);
            }
        });

        mBtnBackToTrips = getActivity().findViewById(R.id.btnBackToTrips);
        mBtnBackToTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_listExpenseFragment_to_listTripFragment);
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(mExpenseArray != null)
                {
                    Expense expense = mExpenseArray.get(position);
                    mExpenseInfoViewModel.setExpense(expense);
                    Navigation.findNavController(view).navigate(R.id.action_listExpenseFragment_to_infoExpenseFragment);
                }
                return false;
            }
        });
    }

    private void setTotalCost(ExpenseArrayAdapter adapter) {
        TextView total = (TextView)getActivity().findViewById(R.id.total_cost);
        total.setText(String.format("%s VND", String.valueOf(adapter.Total_cost())));
    }
}
