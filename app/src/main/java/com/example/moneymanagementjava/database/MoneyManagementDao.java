package com.example.moneymanagementjava.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moneymanagementjava.model.MoneyManagement;

import java.util.List;

@Dao
public interface MoneyManagementDao {

    @Query("Select * from money_management_table order by id")
    LiveData<List<MoneyManagement>> getAllMoneyManagement();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMoneyManagement(MoneyManagement moneyManagement);

    @Delete
    int deleteMoneyManagement(MoneyManagement... moneyManagement);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMoneyManagement(MoneyManagement moneyManagement);
}
