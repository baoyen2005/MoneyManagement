package com.example.moneymanagementjava.database;

import static com.example.moneymanagementjava.constant.ConstantValue.databaseName;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.moneymanagementjava.converter.ConverterClass;
import com.example.moneymanagementjava.model.MoneyManagement;

@Database(entities = {MoneyManagement.class},
        version = 1, exportSchema = false)
@TypeConverters(ConverterClass.class)

public abstract class MoneyManagementDatabase extends RoomDatabase {
    public abstract MoneyManagementDao moneyDao();

    private static MoneyManagementDatabase instance;

    public MoneyManagementDatabase() {

    }

    public static MoneyManagementDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MoneyManagementDatabase.class,
                    databaseName)
                    .build();

        }
        return instance;

    }
    public static void destroyInstance() {
        instance = null;
    }

}
