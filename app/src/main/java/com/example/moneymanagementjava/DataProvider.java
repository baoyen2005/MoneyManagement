package com.example.moneymanagementjava;

import androidx.lifecycle.LiveData;

import com.example.moneymanagementjava.database.MoneyManagementDao;
import com.example.moneymanagementjava.model.MoneyManagement;
import com.example.moneymanagementjava.model.SavingMoney;
import com.example.moneymanagementjava.model.SpendingMoney;

import java.util.List;

interface DataProvider {
    void insertSavingMoney(SavingMoney savingMoney);

    void insertSpendingMoney(SpendingMoney spendingMoney);

    void insertMoneyManagement(MoneyManagement moneyManagement);

    LiveData<List<SpendingMoney>> getAllSpending();

    LiveData<List<SavingMoney>> getAllSavingMoney();

    LiveData<List<MoneyManagement>> getAllMoneyManagement();

    void deleteSavingMoney(SavingMoney savingMoney);

    void deleteSpendingMoney(SpendingMoney spendingMoney);

}

