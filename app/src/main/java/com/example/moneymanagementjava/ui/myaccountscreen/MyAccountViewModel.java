package com.example.moneymanagementjava.ui.myaccountscreen;

import com.example.moneymanagementjava.DataProviderImpl;
import com.example.moneymanagementjava.base.BaseViewModel;
import com.example.moneymanagementjava.model.MoneyManagement;

public class MyAccountViewModel extends BaseViewModel {

    private DataProviderImpl dataProvider;
    private final String TAG = "MyAccountViewModel";


    public MyAccountViewModel(DataProviderImpl dataProvider) {
        this.dataProvider = dataProvider;
    }
    
    public void updateMoneyManagement(MoneyManagement moneyManagement){
        dataProvider.updateMoneyManagement(moneyManagement);
    }

    public void  deleteMoneyManagement(MoneyManagement moneyManagement){
        dataProvider.deleteMoneyManagement(moneyManagement);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }


}
