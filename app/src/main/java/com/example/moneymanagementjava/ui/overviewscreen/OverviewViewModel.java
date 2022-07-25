package com.example.moneymanagementjava.ui.overviewscreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moneymanagementjava.DataProviderImpl;
import com.example.moneymanagementjava.base.BaseViewModel;
import com.example.moneymanagementjava.model.MoneyManagement;

import java.util.List;
public class OverviewViewModel extends BaseViewModel {
    private DataProviderImpl dataProvider;
    private final String TAG = "OverviewViewModel";

    public LiveData<List<MoneyManagement>> allMoneyManagementList = new MutableLiveData();

    public OverviewViewModel(DataProviderImpl dataProvider) {
        this.dataProvider = dataProvider;
    }

    public void getAllMoneyManagementList() {
         allMoneyManagementList = dataProvider.getAllMoneyManagement();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
