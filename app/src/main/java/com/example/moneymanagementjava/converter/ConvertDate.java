package com.example.moneymanagementjava.converter;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
    public String convertDateToString(Date date){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return format.format(date);
    }
    public Date convertStringToDate(String val) throws ParseException {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        return  format.parse(val);
    }
}
