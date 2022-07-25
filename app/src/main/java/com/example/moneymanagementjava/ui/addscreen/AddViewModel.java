package com.example.moneymanagementjava.ui.addscreen;

import com.example.moneymanagementjava.DataProviderImpl;
import com.example.moneymanagementjava.base.BaseViewModel;
import com.example.moneymanagementjava.model.MoneyManagement;

public class AddViewModel extends BaseViewModel {
    private DataProviderImpl dataProvider;
    //private DataProviderImplDemo dataProviderDemo;

    public AddViewModel(DataProviderImpl dataProvider) {
        this.dataProvider = dataProvider;
    }


    public void insertMoneyManagement(MoneyManagement moneyManagement){
        dataProvider.insertMoneyManagement(moneyManagement);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
