package com.example.moneymanagementjava.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;

import com.example.moneymanagementjava.callback.CallBackAddFragment;
import com.example.moneymanagementjava.model.CustomSpinnerItem;

public class DataBindingCustom {

    private CustomSpinnerAdapter spinnerAdapter;
    private CustomSpinnerItem item;
    private String popupMenuValue;
    private static DataBindingCustom INSTANCE;
    private CallBackAddFragment callBackAddFragment;

    public DataBindingCustom(CallBackAddFragment callBackAddFragment) {
        this.callBackAddFragment = callBackAddFragment;
    }

    public static synchronized DataBindingCustom getInstance(CallBackAddFragment callBackAddFragment) {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            return new DataBindingCustom(callBackAddFragment);
        }
    }

    /**
     * laays ra gia tri cua item khi chon trong spinner
     *
     * @param addTittleSpinner : Spinner
     * @param context          : Context
     * @return string
     */

    public void itemSpinnerSelected(Spinner addTittleSpinner, Context context) {
        addTittleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = (CustomSpinnerItem) parent.getSelectedItem();
                callBackAddFragment.spinnerItemValueObserver(item);
                // Toast.makeText(context, item.getSpinnerItemName(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void itemPopupMenu(Context context, ImageView imageView, int menu) {
        PopupMenu popupMenu = new PopupMenu(context, imageView);
        popupMenu.getMenuInflater().inflate(menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            popupMenuValue = item.getTitle().toString();
            callBackAddFragment.popUpMenuValue(popupMenuValue);
            return true;
        });
        popupMenu.show();
    }


}
