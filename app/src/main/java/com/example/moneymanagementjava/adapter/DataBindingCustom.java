package com.example.moneymanagementjava.adapter;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.moneymanagementjava.R;
import com.example.moneymanagementjava.model.CustomSpinnerItem;

public class DataBindingCustom {

    private CustomSpinnerAdapter spinnerAdapter;
    private  CustomSpinnerItem  item ;
    private String popupMenuValue;
    private static DataBindingCustom INSTANCE;
    public DataBindingCustom() {
    }

    public static synchronized DataBindingCustom getInstance(){
        if(INSTANCE != null){
            return  INSTANCE;
        }
        else {
           return new DataBindingCustom();
        }
    }

    /**
     * laays ra gia tri cua item khi chon trong spinner
     * @param addTittleSpinner : Spinner
     * @param context  : Context
     * @return string
     */

    public String itemSpinnerSelected(Spinner addTittleSpinner, Context context){
        addTittleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item  = (CustomSpinnerItem) parent.getSelectedItem();
                Toast.makeText(context, item.getSpinnerItemName(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return item.getSpinnerItemName();
    }

    public String itemPopupMenu(Context context, ImageView imageView, int menu){
        PopupMenu popupMenu = new PopupMenu(context, imageView);
        popupMenu.getMenuInflater().inflate(menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            popupMenuValue = item.getTitle().toString();
            return true;
        });
        popupMenu.show();

        return popupMenuValue;
    }
}
