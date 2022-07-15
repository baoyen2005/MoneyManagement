package com.example.moneymanagementjava.addscreen;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanagementjava.DataProviderImpl;
import com.example.moneymanagementjava.R;
import com.example.moneymanagementjava.adapter.CustomSpinnerAdapter;
import com.example.moneymanagementjava.adapter.DataBindingCustom;
import com.example.moneymanagementjava.base.BaseFragment;
import com.example.moneymanagementjava.base.BaseViewModel;
import com.example.moneymanagementjava.converter.ConvertDate;
import com.example.moneymanagementjava.database.MoneyManagementDao;
import com.example.moneymanagementjava.database.MoneyManagementDatabase;
import com.example.moneymanagementjava.model.CustomSpinnerItem;
import com.example.moneymanagementjava.model.MoneyManagement;

import java.util.ArrayList;
import java.util.Date;


public class AddFragment extends BaseFragment {
    private Spinner addTittleSpinner;
    private CustomSpinnerAdapter spinnerAdapter;
    private ArrayList<CustomSpinnerItem> customList;
    private EditText edtAmountOfMoney;
    private TextView txtContentMoneyType, txtDateAddMoney;
    private ImageView imgNavigateChooseMoneyType, imgDateAddMoney;
    private Button btnSaveMoney;
    private String itemSpinnerName, detailCategory;
    private AddViewModel viewModel;
    private ConvertDate convertDate;
    private final String TAG = "addfragment";

    @Override
    protected int layoutId() {
        return R.layout.fragment_add;
    }

    @Override
    protected BaseViewModel viewModel() {

        viewModel = new AddViewModel(new DataProviderImpl(MoneyManagementDatabase.getInstance(requireActivity()).moneyDao()));
        //  viewModel = new AddViewModel(new DataProviderImplDemo(DemoRoomData.getInstance(requireActivity())));

        return viewModel;
    }

    /**
     * khowri tao view
     */
    @Override
    protected void initView(View view) {
        addTittleSpinner = view.findViewById(R.id.addTittleSpinner);
        edtAmountOfMoney = view.findViewById(R.id.edtAmountOfMoney);
        txtContentMoneyType = view.findViewById(R.id.txtContentMoneyType);
        txtDateAddMoney = view.findViewById(R.id.txtDateAddMoney);
        imgNavigateChooseMoneyType = view.findViewById(R.id.imgNavigateChooseMoneyType);
        imgDateAddMoney = view.findViewById(R.id.imgDateAddMoney);
        btnSaveMoney = view.findViewById(R.id.btnSaveMoney);
        customList = getCustomlist();
        convertDate = new ConvertDate();
        spinnerAdapter = new CustomSpinnerAdapter(requireContext(), customList);
        if (addTittleSpinner != null) {
            addTittleSpinner.setAdapter(spinnerAdapter);

//            addTittleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    CustomSpinnerItem item  = (CustomSpinnerItem) parent.getSelectedItem();
//                    itemSpinnerName = item.getSpinnerItemName();
//                    Toast.makeText(requireActivity(), item.getSpinnerItemName(),Toast.LENGTH_SHORT).show();
//
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });

            itemSpinnerName = DataBindingCustom.getInstance().itemSpinnerSelected(addTittleSpinner, requireContext());
            Log.d(TAG, "initView: itemSpinnerName = "+itemSpinnerName);
        }
    }

    /**
     * xu ly cac su kien cua cac view
     */
    @Override
    protected void eventView() {
        getDetailCategory();
        handleAddedDate();
        saveDataToDabase();
    }

    /**
     * khowir tao list dunfg cho spinner tittle
     *
     * @return Arraylist<CustomSpinnerItem></CustomSpinnerItem>
     */

    private ArrayList<CustomSpinnerItem> getCustomlist() {
        customList = new ArrayList<>();
        CustomSpinnerItem item1 = new CustomSpinnerItem(R.drawable.icon_minus_spending_money, "Spending");
        CustomSpinnerItem item2 = new CustomSpinnerItem(R.drawable.icon_add_collect_money, "Collecting");

        customList.add(item1);
        customList.add(item2);
        return customList;
    }

    /**
     * neu khong chon ngay thi lay gia tri ngay hom nay
     */
    private void handleAddedDate() {
        txtDateAddMoney.setText(convertDate.convertDateToString(new Date()));
        imgDateAddMoney.setOnClickListener(view -> {
            DatePickerFragmentDialog dialog = new DatePickerFragmentDialog(txtDateAddMoney);
            dialog.show(requireActivity().getSupportFragmentManager(), "date picker");
        });
    }

    /**
     * navigateto de lay gia tri tieu pha chi tiet
     */
    private void getDetailCategory() {
        Log.d(TAG, "getDetailCategory: itemSpinnerName = " + itemSpinnerName);
        imgNavigateChooseMoneyType.setOnClickListener(view -> {
            switch (itemSpinnerName) {
                case "Spending": {
                    detailCategory = DataBindingCustom.getInstance().itemPopupMenu(
                            requireContext(), imgNavigateChooseMoneyType, R.menu.menu_expand_detail_category_for_spending
                    );
                    Log.d(TAG, "getDetailCategory: Spending - " + detailCategory);
                    break;
                }
                case "Collecting": {
                    detailCategory = DataBindingCustom.getInstance().itemPopupMenu(
                            requireContext(), imgNavigateChooseMoneyType, R.menu.menu_expand_detail_category_for_collecting
                    );
                    Log.d(TAG, "getDetailCategory: Collecting - " + detailCategory);
                    break;
                }
            }
        });
        txtContentMoneyType.setText(detailCategory);
    }

    /**
     * luu du lieu vao database sd async task
     */
    private void saveDataToDabase() {
        btnSaveMoney.setOnClickListener(view -> {
            MoneyManagement moneyManagement = new MoneyManagement();
            moneyManagement.setCategory(itemSpinnerName);
            moneyManagement.setDetailCategory(detailCategory);
            moneyManagement.setAddedDate(convertDate.convertStringToDate(txtDateAddMoney.getText().toString()));
            moneyManagement.setTotalMoney(Float.parseFloat(edtAmountOfMoney.getText().toString()));
            AsyncTask.execute(() -> {
                viewModel.insertMoneyManagement(moneyManagement);
                Handler handler = new Handler(requireContext().getMainLooper());
                handler.post(
                        () -> Toast.makeText(requireContext(), "Insert successfully", Toast.LENGTH_SHORT).show());
                Log.d(TAG, "saveDataToDabase: successfully");
            });
        });
    }
}