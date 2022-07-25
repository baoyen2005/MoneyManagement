package com.example.moneymanagementjava;

import androidx.lifecycle.LiveData;

import com.example.moneymanagementjava.model.MoneyManagement;

import java.util.List;

interface DataProvider {

    void insertMoneyManagement(MoneyManagement moneyManagement);

    LiveData<List<MoneyManagement>> getAllMoneyManagement();

    int  deleteMoneyManagement(MoneyManagement... moneyManagement);

    void updateMoneyManagement(MoneyManagement moneyManagement);

}

