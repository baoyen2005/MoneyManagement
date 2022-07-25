package com.example.moneymanagementjava.constant;

import static com.example.moneymanagementjava.constant.ConstantValue.collecting;
import static com.example.moneymanagementjava.constant.ConstantValue.spending;

import android.content.Context;
import android.widget.ImageView;

import com.example.moneymanagementjava.R;
import com.example.moneymanagementjava.adapter.DataBindingCustom;
import com.example.moneymanagementjava.callback.CallBackAddFragment;
import com.example.moneymanagementjava.model.CustomSpinnerItem;

import java.util.ArrayList;
import java.util.List;

public class ConstantFunction {
    private static ConstantFunction INSTANCE;
    public static synchronized ConstantFunction getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            return new ConstantFunction();
        }
    }

    public ArrayList<CustomSpinnerItem> getCustomlist() {
        List<CustomSpinnerItem> customList = new ArrayList<>();
        CustomSpinnerItem item1 = new CustomSpinnerItem(customList.size(), R.drawable.icon_minus_spending_money, spending);
        customList.add(item1);
        CustomSpinnerItem item2 = new CustomSpinnerItem(customList.size(), R.drawable.icon_add_collect_money, collecting);
        customList.add(item2);
        return (ArrayList<CustomSpinnerItem>) customList;
    }

    public void chooseDetailCategory(Context context,String itemSpinnerName,
                                     ImageView imageView,
                                     CallBackAddFragment callBackAddFragment){
        switch (itemSpinnerName) {
            case spending: {
                DataBindingCustom.getInstance(callBackAddFragment).itemPopupMenu(
                        context, imageView, R.menu.menu_expand_detail_category_for_spending
                );

                break;
            }
            case collecting: {
                DataBindingCustom.getInstance(callBackAddFragment).itemPopupMenu(
                        context, imageView, R.menu.menu_expand_detail_category_for_collecting
                );
                break;
            }
        }
    }

}
