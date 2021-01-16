package com.example.tripplannew;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class PieChartFragment extends Fragment {
    TextView tvFood, tvBeverage, tvMove, tvPhone,tvEntertainmet, tvOtherActive;
    PieChart pieChart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_pie_chart,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvBeverage = getActivity().findViewById(R.id.tvBeverage);
        tvFood = getActivity().findViewById(R.id.tvFood);
        tvEntertainmet = getActivity().findViewById(R.id.tvEntertainment);
        tvMove = getActivity().findViewById(R.id.tvMove);
        tvOtherActive= getActivity().findViewById(R.id.tvOtherActive);
        tvPhone=getActivity().findViewById(R.id.tvPhone);
        pieChart = getActivity().findViewById(R.id.piechart);
        setData();

    }

    private void setData() {
        tvBeverage.setText(Integer.toString(40));
        tvFood.setText(Integer.toString(30));
        tvOtherActive.setText(Integer.toString(5));
        tvPhone.setText(Integer.toString(25));
        tvMove.setText(Integer.toString(25));
        tvEntertainmet.setText(Integer.toString(25));
        pieChart.addPieSlice(
                new PieModel(
                        "Đồ uống",
                        Integer.parseInt(tvBeverage.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Đồ ăn",
                        Integer.parseInt(tvFood.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Di chuyển",
                        Integer.parseInt(tvMove.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Điện thoại",
                        Integer.parseInt(tvPhone.getText().toString()),
                        Color.parseColor("#29B6F6")));
        pieChart.addPieSlice(
                new PieModel(
                        "Java",
                        Integer.parseInt(tvEntertainmet.getText().toString()),
                        Color.parseColor("#9966CC")));
        pieChart.addPieSlice(
                new PieModel(
                        "Hoạt động khác",
                        Integer.parseInt(tvOtherActive.getText().toString()),
                        Color.parseColor("#FFFF66")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}
