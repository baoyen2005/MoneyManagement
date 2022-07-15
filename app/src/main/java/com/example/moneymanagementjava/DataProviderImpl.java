package com.example.moneymanagementjava;

import androidx.lifecycle.LiveData;

import com.example.moneymanagementjava.database.MoneyManagementDao;
import com.example.moneymanagementjava.model.MoneyManagement;
import com.example.moneymanagementjava.model.SavingMoney;
import com.example.moneymanagementjava.model.SpendingMoney;

import java.util.List;

public class DataProviderImpl implements DataProvider {
    private MoneyManagementDao managementDao;

    public DataProviderImpl(MoneyManagementDao managementDao) {
        this.managementDao = managementDao;
    }

    @Override
    public void insertSavingMoney(SavingMoney savingMoney) {
        managementDao.insertSavingMoney(savingMoney);
    }

    @Override
    public void insertSpendingMoney(SpendingMoney spendingMoney) {
        managementDao.insertSpendingMoney(spendingMoney);
    }

    @Override
    public void insertMoneyManagement(MoneyManagement moneyManagement) {
        managementDao.insertMoneyManagement(moneyManagement);
    }

    @Override
    public LiveData<List<SpendingMoney>> getAllSpending() {
        return managementDao.getAllSpending();
    }

    @Override
    public LiveData<List<SavingMoney>> getAllSavingMoney() {
        return managementDao.getAllSavingMoney();
    }

    @Override
    public LiveData<List<MoneyManagement>> getAllMoneyManagement() {
        return managementDao.getAllMoneyManagement();
    }

    @Override
    public void deleteSavingMoney(SavingMoney savingMoney) {
        managementDao.deleteSavingMoney(savingMoney);
    }

    @Override
    public void deleteSpendingMoney(SpendingMoney spendingMoney) {
        managementDao.deleteSpendingMoney(spendingMoney);
    }
}
