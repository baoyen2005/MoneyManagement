package com.example.moneymanagementjava;

import androidx.lifecycle.LiveData;

import com.example.moneymanagementjava.database.MoneyManagementDao;
import com.example.moneymanagementjava.model.MoneyManagement;

import java.util.List;

public class DataProviderImpl implements DataProvider {
    private MoneyManagementDao managementDao;

    public DataProviderImpl(MoneyManagementDao managementDao) {
        this.managementDao = managementDao;
    }
    @Override
    public void insertMoneyManagement(MoneyManagement moneyManagement) {
        managementDao.insertMoneyManagement(moneyManagement);
    }

    @Override
    public LiveData<List<MoneyManagement>> getAllMoneyManagement() {
        return managementDao.getAllMoneyManagement();
    }

    @Override
    public int deleteMoneyManagement(MoneyManagement... moneyManagement) {
        return managementDao.deleteMoneyManagement(moneyManagement);
    }

    @Override
    public void updateMoneyManagement(MoneyManagement moneyManagement) {
        managementDao.updateMoneyManagement(moneyManagement);
    }
}
