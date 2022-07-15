package com.example.moneymanagementjava.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "spending_table")
public class SpendingMoney {

    @PrimaryKey(autoGenerate = true)
    private int spendingId = 0;

    @ColumnInfo
    private String spendingTitle = "";

    @ColumnInfo
    private String spendingDetailNote  = "";

    public int getSpendingId() {
        return spendingId;
    }

    public void setSpendingId(int spendingId) {
        this.spendingId = spendingId;
    }

    public String getSpendingTitle() {
        return spendingTitle;
    }

    public void setSpendingTitle(String spendingTitle) {
        this.spendingTitle = spendingTitle;
    }

    public String getSpendingDetailNote() {
        return spendingDetailNote;
    }

    public void setSpendingDetailNote(String spendingDetailNote) {
        this.spendingDetailNote = spendingDetailNote;
    }

    public float getSpendingMoney() {
        return spendingMoney;
    }

    public void setSpendingMoney(float spendingMoney) {
        this.spendingMoney = spendingMoney;
    }

    public Date getSpendingDay() {
        return spendingDay;
    }

    public void setSpendingDay(Date spendingDay) {
        this.spendingDay = spendingDay;
    }

    @ColumnInfo
    float spendingMoney = 0f;

    @ColumnInfo
    Date spendingDay  = new Date();
}
