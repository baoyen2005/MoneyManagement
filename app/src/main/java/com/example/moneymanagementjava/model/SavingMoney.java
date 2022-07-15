package com.example.moneymanagementjava.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "saving_money_table")
public class SavingMoney {


    @PrimaryKey(autoGenerate = true)
    private int savingMoneyId  = 0;

    @ColumnInfo
    private float totalSavingMoney = 0f;

    @ColumnInfo
    private Date savingDay = new Date();

    public int getSavingMoneyId() {
        return savingMoneyId;
    }

    public void setSavingMoneyId(int savingMoneyId) {
        this.savingMoneyId = savingMoneyId;
    }

    public float getTotalSavingMoney() {
        return totalSavingMoney;
    }

    public void setTotalSavingMoney(float totalSavingMoney) {
        this.totalSavingMoney = totalSavingMoney;
    }

    public Date getSavingDay() {
        return savingDay;
    }

    public void setSavingDay(Date savingDay) {
        this.savingDay = savingDay;
    }
}
