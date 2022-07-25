package com.example.moneymanagementjava.ui.addscreen;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragmentDialog extends DialogFragment implements android.app.DatePickerDialog.OnDateSetListener{

    private final TextView textView;
    private final String TAG = "DatePickerFragmentDialog";

    public DatePickerFragmentDialog(TextView textView) {
        this.textView = textView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),this,year,month,day);
        dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        return dialog;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        textView.setText(dayOfMonth + "/"+ month +"/"+ year +" " + simpleDateFormat.format(now));
        Log.d(TAG, "onDateSet: + textview set date - "+ textView.getText().toString());

    }
}
