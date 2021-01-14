package com.example.tripplannew.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tripplannew.R;
import com.example.tripplannew.data.Expense;

import java.util.List;

public class ExpenseArrayAdapter extends ArrayAdapter<Expense> {
    private Context context;
    private List<Expense> ExpenseList;

    public ExpenseArrayAdapter(@NonNull Context context, @NonNull List<Expense> list) {
        super(context, 0, list);
        this.context = context;
        this.ExpenseList = list;
    }

    public void add_item(Expense expense){
        ExpenseList.add(expense);
    }
    public int Total_cost()
    {
        int cost = 0;
        for (int i = 0; i < ExpenseList.size(); i++)
            if (ExpenseList.get(i).getCost() != 0)
                cost += ExpenseList.get(i).getCost();
        return cost;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = LayoutInflater.from(context).inflate(R.layout.layout_item_expense, null);
        ImageView img = (view).findViewById(R.id.Image);
        TextView cost = (view).findViewById(R.id.cost);
        TextView type = (view).findViewById(R.id.type);
        Expense expense = ExpenseList.get(position);

        img.setImageResource(expense.getBitMapID());
        cost.setText(String.format("%s VND", (int)expense.getCost()));
        type.setText(String.format("%s", expense.getType()));
        return view;
        //return super.getView(position, convertView, parent);
    }

}
