package com.example.moneymanagementjava.converter;

import android.annotation.SuppressLint;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterClass {

    @TypeConverter
    public Date toDate(String date){
        return new Gson().fromJson(date, Date.class);
    }

    @TypeConverter
    public String fromDate(Date date){
        return new Gson().toJson(date);
    }
}
