package com.example.moneymanagementjava.converter;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
    public String convertDateToString(Date date){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return format.format(date);
    }
    public Date convertStringToDate(String val){
        Date valueDate = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            valueDate = format.parse(val);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  valueDate;
    }
}
