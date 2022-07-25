package com.example.moneymanagementjava.ui.overviewscreen;

import static com.example.moneymanagementjava.constant.ConstantValue.collecting;
import static com.example.moneymanagementjava.constant.ConstantValue.spending;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanagementjava.DataProviderImpl;
import com.example.moneymanagementjava.R;
import com.example.moneymanagementjava.adapter.DataBindingCustom;
import com.example.moneymanagementjava.base.BaseFragment;
import com.example.moneymanagementjava.base.BaseViewModel;
import com.example.moneymanagementjava.callback.CallBackAddFragment;
import com.example.moneymanagementjava.database.MoneyManagementDatabase;
import com.example.moneymanagementjava.model.CustomSpinnerItem;
import com.example.moneymanagementjava.model.MoneyManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OverviewFragment extends BaseFragment implements CallBackAddFragment {
    private TextView txtTime, txtCollectingMoney,
            txtSpendingMoney, txtChange, txtTotalBalance1;
    private ImageView imgExpandChooseTime;
    private OverviewViewModel viewModel;
    private final String TAG = "OverviewFragment";
    private String time ="This month";
    private float totalSpending = 0;
    private float totalCollecting = 0;
    private float totalChange = 0;
    private float totalBalance = 0;
    private List<MoneyManagement> moneyManagementList = new ArrayList<>();

    public OverviewFragment() {
        super();
    }



    @Override
    protected int layoutId() {
        return R.layout.fragment_overview;
    }

    @Override
    protected BaseViewModel viewModel() {
        viewModel = new OverviewViewModel(new DataProviderImpl(MoneyManagementDatabase.getInstance(requireContext()).moneyDao()));
        viewModel.getAllMoneyManagementList();
        return viewModel;
    }

    @Override
    protected void initView(View view) {
        txtTime = view.findViewById(R.id.txtTime);
        txtCollectingMoney = view.findViewById(R.id.txtCollectingMoney);
        txtSpendingMoney = view.findViewById(R.id.txtSpendingMoney);
        txtChange = view.findViewById(R.id.txtChange);
        txtTotalBalance1 = view.findViewById(R.id.txtTotalBalance1);
        imgExpandChooseTime = view.findViewById(R.id.imgExpandChooseTime);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void eventView() {
        setPopupMenu();
        setValueUseObserve();

    }

    @SuppressLint("SetTextI18n")
    private void setValueUseObserve() {
        viewModel.allMoneyManagementList.observe(this, moneyManagements -> {

            if (moneyManagements == null || moneyManagements.size() == 0) {
                Toast.makeText(requireContext(), "No data in database. Please add data to database", Toast.LENGTH_SHORT).show();
            } else {
                moneyManagementList.clear();
                moneyManagementList.addAll( moneyManagements);
                totalBalance = 0;
                totalChange = 0;
                totalCollecting = 0;
                totalSpending = 0;
                for (int i = 0; i < moneyManagementList.size(); i++) {
                    totalBalance += moneyManagementList.get(i).getTotalMoney();
                }
                Log.d(TAG, "setValueUseObserve: totalBalance = " + totalBalance + "");
                txtTotalBalance1.setText(totalBalance + "");
                if (moneyManagementList != null || moneyManagementList.size() > 0) {
                    Log.d(TAG, "setPopupMenu: moneyManagementList.size = " + moneyManagementList.size());
                    if (time.equals("This month")) {
                        totalSpending = getTotalByMonth(moneyManagementList, spending);
                        totalCollecting = getTotalByMonth(moneyManagementList, collecting);
                        totalChange = totalCollecting - totalSpending;
                    }
                    else if (time.equals("This year")) {
                        totalSpending = getTotalByYear(moneyManagementList, spending);
                        totalCollecting = getTotalByYear(moneyManagementList, collecting);
                        Log.d("__tag", "setValueUseObserve: 2");
                    }
                    txtChange.setText(totalChange + "");
                    txtSpendingMoney.setText(totalSpending + "");
                    txtCollectingMoney.setText(totalCollecting + "");
                    Log.d(TAG, "popUpMenuValue: value " + time);
                } else {
                    Toast.makeText(requireContext(), "Please wait load data", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private float getTotalByMonth(List<MoneyManagement> moneyManagements, String title) {
        float total = 0;
        for (int i = 0; i < moneyManagements.size(); i++) {
            if (moneyManagements.get(i).getCategory().equals(title)) {
                if (moneyManagements.get(i).getAddedDate().getMonth() == (new Date()).getMonth()) {
                    total += moneyManagements.get(i).getTotalMoney();
                }
            }
        }
        return total;
    }

    private float getTotalByYear(List<MoneyManagement> moneyManagements, String title) {
        float total = 0;
        boolean check = false;
        for (int i = 0; i < moneyManagements.size(); i++) {
            if (moneyManagements.get(i).getCategory().equals(title)) {
                if (moneyManagements.get(i).getAddedDate().getYear() == (new Date()).getYear()) {
                    total += moneyManagements.get(i).getTotalMoney();
                    check  = true;
                }

            }
            else{
                check = false;
            }
        }
        if(check)  return total;
        else return 0;
    }

    @SuppressLint("SetTextI18n")
    private void setPopupMenu() {
        imgExpandChooseTime.setOnClickListener(v -> {
            DataBindingCustom.getInstance(this).itemPopupMenu(
                    requireContext(), imgExpandChooseTime, R.menu.menu_expand_time
            );

        });
    }

    @Override
    public void spinnerItemValueObserver(CustomSpinnerItem item) {
        Log.d(TAG, "spinnerItemValueObserver: ");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void popUpMenuValue(String value) {
        txtTime.setText(value);
        time = value;
    }
}