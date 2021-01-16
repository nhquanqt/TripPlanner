package com.example.tripplannew.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tripplannew.data.webservice.Trip;

import java.util.ArrayList;

import com.example.tripplannew.R;

public class TripArrayAdapter extends ArrayAdapter<Trip> {
    public TripArrayAdapter(Context context, ArrayList<Trip> items) {
        super(context, 0, items);
    }

    public static class ViewHolder {
        TextView tvName;
        TextView tvBudget;
        Button btRemove;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_trip, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName=(TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvBudget=(TextView) convertView.findViewById(R.id.tvBudget);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Trip item = getItem(position);
        bindData(item, viewHolder);
        return convertView;
        //return super.getView(position, convertView, parent);
    }

    private void bindData(Trip item, ViewHolder viewHolder) {
        viewHolder.tvName.setText(item.getTripName());
        viewHolder.tvBudget.setText(String.format("%s VND", String.valueOf((int)item.getBudget())));
        // TextView    tvName=(TextView) convertView.findViewById(R.id.tvName);
        // TextView tvBg       =(TextView) convertView.findViewById(R.id.tvBudget);
        // tvName.setText(item.name);
        // tvBg.setText(item.budget);
    }
}
