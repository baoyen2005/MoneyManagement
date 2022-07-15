package com.example.moneymanagementjava.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "money_management_table")
public class MoneyManagement {

    @PrimaryKey(autoGenerate = true)
    private int id  = 0;

    @ColumnInfo
    private String category = "";

    @ColumnInfo
    private String detailCategory = "";

    @ColumnInfo
    private float totalMoney  = 0f;

    @ColumnInfo
    private Date addedDate = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetailCategory() {
        return detailCategory;
    }

    public void setDetailCategory(String detailCategory) {
        this.detailCategory = detailCategory;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}
