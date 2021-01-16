package com.example.tripplannew;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tripplannew.data.webservice.Expense;
import com.example.tripplannew.viewmodels.ExpenseListViewModel;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;

public class PieChartFragment extends Fragment {
    TextView tvFood, tvBeverage, tvMove, tvRent, tvPhone, tvEntertainment, tvOtherActive;
    PieChart pieChart;

    ExpenseListViewModel mExpenseListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mExpenseListViewModel = new ViewModelProvider(getActivity()).get(ExpenseListViewModel.class);

        return inflater.inflate(R.layout.fragment_pie_chart,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvFood = getActivity().findViewById(R.id.tvFood);
        tvBeverage = getActivity().findViewById(R.id.tvBeverage);
        tvMove = getActivity().findViewById(R.id.tvMove);
        tvRent = getActivity().findViewById(R.id.tvRent);
        tvEntertainment = getActivity().findViewById(R.id.tvEntertainment);
        tvPhone=getActivity().findViewById(R.id.tvPhone);
        tvOtherActive= getActivity().findViewById(R.id.tvOtherActive);

        pieChart = getActivity().findViewById(R.id.piechart);
        setData();

    }

    @SuppressLint("SetTextI18n")
    private void setData() {

        ArrayList<Expense> expenses = mExpenseListViewModel.getExpenses();

        float costs[] = {0,0,0,0,0,0,0};

        for(int i = 0; i < expenses.size(); ++i)
        {
            Expense expense = expenses.get(i);
            costs[expense.getType()] += expense.getCost();
        }

        tvFood.setText(Integer.toString((int)costs[0]));
        tvBeverage.setText(Integer.toString((int)costs[1]));
        tvMove.setText(Integer.toString((int)costs[2]));
        tvRent.setText(Integer.toString((int)costs[3]));
        tvEntertainment.setText(Integer.toString((int)costs[4]));
        tvPhone.setText(Integer.toString((int)costs[5]));
        tvOtherActive.setText(Integer.toString((int)costs[6]));

        pieChart.addPieSlice(
                new PieModel(
                        "Đồ ăn",
                        Integer.parseInt(tvFood.getText().toString()),
                        getResources().getColor(R.color.Food)
                )
        );
        pieChart.addPieSlice(
                new PieModel(
                        "Đồ uống",
                        Integer.parseInt(tvBeverage.getText().toString()),
                        getResources().getColor(R.color.Beverage)
                )
        );
        pieChart.addPieSlice(
                new PieModel(
                        "Di chuyển",
                        Integer.parseInt(tvMove.getText().toString()),
                        getResources().getColor(R.color.Move)
                )
        );
        pieChart.addPieSlice(
                new PieModel(
                        "Thuê địa điểm",
                        Integer.parseInt(tvRent.getText().toString()),
                        getResources().getColor(R.color.Rent)
                )
        );
        pieChart.addPieSlice(
                new PieModel(
                        "Hoạt động giải trí",
                        Integer.parseInt(tvEntertainment.getText().toString()),
                        getResources().getColor(R.color.Entertainment)
                )
        );
        pieChart.addPieSlice(
                new PieModel(
                        "Điện thoại",
                        Integer.parseInt(tvPhone.getText().toString()),
                        getResources().getColor(R.color.Phone)
                )
        );
        pieChart.addPieSlice(
                new PieModel(
                        "Hoạt động khác",
                        Integer.parseInt(tvOtherActive.getText().toString()),
                        getResources().getColor(R.color.OtherActive)
                )
        );

        // To animate the pie chart
        pieChart.startAnimation();
    }
}
