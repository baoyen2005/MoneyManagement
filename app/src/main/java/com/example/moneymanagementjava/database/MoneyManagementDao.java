package com.example.moneymanagementjava.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.moneymanagementjava.model.MoneyManagement;
import com.example.moneymanagementjava.model.SavingMoney;
import com.example.moneymanagementjava.model.SpendingMoney;

import java.util.List;

@Dao
public interface MoneyManagementDao {

    @Transaction
    @Query("Select * from spending_table order by spendingId asc")
    public LiveData<List<SpendingMoney>> getAllSpending();

    @Transaction
    @Query("Select * from saving_money_table order by savingMoneyId asc")
    public LiveData<List<SavingMoney>> getAllSavingMoney();

    @Transaction
    @Query("Select * from money_management_table order by id")
    public LiveData<List<MoneyManagement>> getAllMoneyManagement();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSavingMoney(SavingMoney savingMoney);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSpendingMoney( SpendingMoney spendingMoney);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMoneyManagement(MoneyManagement moneyManagement);

    @Delete
    void deleteSavingMoney(SavingMoney savingMoney);

    @Delete
    void deleteSpendingMoney(SpendingMoney spendingMoney);

    @Delete
    void deleteMoneyManagement(MoneyManagement moneyManagement);
}
