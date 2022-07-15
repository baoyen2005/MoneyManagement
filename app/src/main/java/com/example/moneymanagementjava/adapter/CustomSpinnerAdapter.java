package com.example.moneymanagementjava.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.moneymanagementjava.R;
import com.example.moneymanagementjava.model.CustomSpinnerItem;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter {

    public CustomSpinnerAdapter(@NotNull Context context, ArrayList<CustomSpinnerItem> customSpinnerItemArrayList) {
        super(context, 0, customSpinnerItemArrayList);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_sppiner_layout, parent, false);
        }
        CustomSpinnerItem item = (CustomSpinnerItem) getItem(position);
        ImageView spinnerIV = convertView.findViewById(R.id.imgContentSpinnerAddLayout);
        TextView spinnerTV = convertView.findViewById(R.id.txtContentSpinnerAddLayout);
        if (item != null) {
            spinnerTV.setText(item.getSpinnerItemName());
            spinnerIV.setImageResource(item.getSpinnerItemImage());

        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dropdown_add_layout, parent, false);
        }
        CustomSpinnerItem item = (CustomSpinnerItem) getItem(position);
        ImageView dropDownIV = convertView.findViewById(R.id.imgDropDownAddLayout);
        TextView dropDownTV = convertView.findViewById(R.id.txtDropDownAddLayout);
        if (item != null) {
            dropDownTV.setText(item.getSpinnerItemName());
            dropDownIV.setImageResource(item.getSpinnerItemImage());

        }
        return convertView;    }
}
