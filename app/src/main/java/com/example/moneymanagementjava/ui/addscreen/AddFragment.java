package com.example.moneymanagementjava.addscreen;

import static com.example.moneymanagementjava.constant.ConstantValue.collecting;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
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
import com.example.moneymanagementjava.callback.CallBackAddFragment;
import com.example.moneymanagementjava.constant.ConstantFunction;
import com.example.moneymanagementjava.converter.ConvertDate;
import com.example.moneymanagementjava.database.MoneyManagementDatabase;
import com.example.moneymanagementjava.model.CustomSpinnerItem;
import com.example.moneymanagementjava.model.MoneyManagement;
import com.example.moneymanagementjava.ui.addscreen.AddViewModel;
import com.example.moneymanagementjava.ui.addscreen.DatePickerFragmentDialog;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class AddFragment extends BaseFragment implements CallBackAddFragment {

    private EditText edtAmountOfMoney;
    private TextView txtContentMoneyType, txtDateAddMoney;
    private ImageView imgNavigateChooseMoneyType, imgDateAddMoney;
    private Button btnSaveMoney;
    private AddViewModel viewModel;
    private String itemSpinnerName = collecting, detailCategory;
    private ConvertDate convertDate;
    private int spinnerItemId = 0;
    private final String TAG = "addfragment";

    @Override
    protected int layoutId() {
        return R.layout.fragment_add;
    }

    @Override
    protected BaseViewModel viewModel() {

        viewModel = new AddViewModel(new DataProviderImpl(MoneyManagementDatabase.getInstance(requireActivity()).moneyDao()));

        return viewModel;
    }

    /**
     * khowri tao view
     */
    @Override
    protected void initView(View view) {
        Spinner addTittleSpinner = view.findViewById(R.id.addTittleSpinner);
        edtAmountOfMoney = view.findViewById(R.id.edtAmountOfMoney);
        txtContentMoneyType = view.findViewById(R.id.txtContentMoneyType);
        txtDateAddMoney = view.findViewById(R.id.txtDateAddMoney);
        imgNavigateChooseMoneyType = view.findViewById(R.id.imgNavigateChooseMoneyType);
        imgDateAddMoney = view.findViewById(R.id.imgDateAddMoney);
        btnSaveMoney = view.findViewById(R.id.btnSaveMoney);
        ArrayList<CustomSpinnerItem> customList = ConstantFunction.getInstance().getCustomlist();
        convertDate = new ConvertDate();
        CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(requireContext(), customList);
        if (addTittleSpinner != null) {
            addTittleSpinner.setAdapter(spinnerAdapter);
            DataBindingCustom.getInstance(this).itemSpinnerSelected(addTittleSpinner, requireContext());

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
     * neu khong chon ngay thi lay gia tri ngay hom nay
     */
    private void handleAddedDate() {
        Calendar cal = Calendar.getInstance();
        txtDateAddMoney.setText(convertDate.convertDateToString(cal.getTime()));
        Log.d(TAG, "handleAddedDate: "+convertDate.convertDateToString(new Date()));
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
            ConstantFunction.getInstance().chooseDetailCategory(requireContext(),
                    itemSpinnerName,
                    imgNavigateChooseMoneyType,
                    this
            );
//            switch (itemSpinnerName) {
//                case spending: {
//                    DataBindingCustom.getInstance(this).itemPopupMenu(
//                            requireContext(), imgNavigateChooseMoneyType, R.menu.menu_expand_detail_category_for_spending
//                    );
//
//                    break;
//                }
//                case collecting: {
//                    DataBindingCustom.getInstance(this).itemPopupMenu(
//                            requireContext(), imgNavigateChooseMoneyType, R.menu.menu_expand_detail_category_for_collecting
//                    );
//                    break;
//                }
//            }
        });
    }

    /**
     * luu du lieu vao database sd async task
     */
    private void saveDataToDabase() {
        btnSaveMoney.setOnClickListener(view -> {
            MoneyManagement moneyManagement = new MoneyManagement();

            moneyManagement.setCategory(itemSpinnerName);
            moneyManagement.setDetailCategory(detailCategory);
            try {
                Log.d(TAG, "saveDataToDabase: txtDateAddMoney.getText().toString()) = "
                        +convertDate.convertStringToDate(txtDateAddMoney.getText().toString()).toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                moneyManagement.setAddedDate(convertDate.convertStringToDate(txtDateAddMoney.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            moneyManagement.setTotalMoney(Float.parseFloat(edtAmountOfMoney.getText().toString()));
            moneyManagement.setSpinnerItemId(spinnerItemId);

            AsyncTask.execute(() -> {
                viewModel.insertMoneyManagement(moneyManagement);

                Handler handler = new Handler(requireContext().getMainLooper());
                handler.post(
                        () -> Toast.makeText(requireContext(), "Insert successfully", Toast.LENGTH_SHORT).show());
                edtAmountOfMoney.setText("");
                txtContentMoneyType.setText("");
                txtDateAddMoney.setText(convertDate.convertDateToString(new Date()));
                Log.d(TAG, "saveDataToDabase: successfully");
            });
        });
    }

    @Override
    public void spinnerItemValueObserver(CustomSpinnerItem item) {
        itemSpinnerName = item.getSpinnerItemName();
        spinnerItemId = item.getId();
        Log.d(TAG, "spinnerItemValueObserver: itemSpinnerName = " + itemSpinnerName);
        Log.d(TAG, "spinnerItemValueObserver: spinnerItemId = " + spinnerItemId);

    }

    @Override
    public void popUpMenuValue(String value) {
        detailCategory = value;
        Log.d(TAG, "popUpMenuValue: detailCategory = " + detailCategory);
        txtContentMoneyType.setText(detailCategory);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}