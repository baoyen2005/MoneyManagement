package com.example.moneymanagementjava.callback;

import com.example.moneymanagementjava.model.CustomSpinnerItem;

public interface CallBackAddFragment {

    public void spinnerItemValueObserver(CustomSpinnerItem item);

    void popUpMenuValue(String value);

}
