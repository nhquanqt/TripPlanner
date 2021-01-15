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
import com.example.tripplannew.data.webservice.Expense;

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

        int bitmapId;
        String typeName;
        switch (expense.getType()) {
            case Expense.TYPE_FOOD:
                bitmapId = R.drawable.food;
                typeName = "Đồ ăn";
                break;
            case Expense.TYPE_DRINK:
                bitmapId = R.drawable.drink;
                typeName = "Đồ uống";
                break;
            case Expense.TYPE_MOVE:
                bitmapId = R.drawable.icon_car;
                typeName = "Di chuyển";
                break;
            case Expense.TYPE_RENT:
                bitmapId = R.drawable.icon_house;
                typeName = "Thuê địa điểm";
                break;
            case Expense.TYPE_ENTERTAINMENT:
                bitmapId = R.drawable.icon_entertaintment;
                typeName = "Hoạt động giải trí";
                break;
            case Expense.TYPE_PHONE:
                bitmapId = R.drawable.icon_phone;
                typeName = "Điện thoại";
                break;
            case Expense.TYPE_OTHER:
                bitmapId = R.drawable.icon_other;
                typeName = "Khoản chi khác";
                break;

            default:
                bitmapId = R.drawable.icon_other;
                typeName = "";
        }
        img.setImageResource(bitmapId);
        cost.setText(String.format("%s VND", (int)expense.getCost()));
        type.setText(String.format("%s", typeName));
        return view;
        //return super.getView(position, convertView, parent);
    }

}
