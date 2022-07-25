package com.example.moneymanagementjava.ui.myaccountscreen;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagementjava.DataProviderImpl;
import com.example.moneymanagementjava.R;
import com.example.moneymanagementjava.adapter.AccountAdapter;
import com.example.moneymanagementjava.adapter.CustomSpinnerAdapter;
import com.example.moneymanagementjava.adapter.DataBindingCustom;
import com.example.moneymanagementjava.base.BaseFragment;
import com.example.moneymanagementjava.base.BaseViewModel;
import com.example.moneymanagementjava.callback.CallBackAddFragment;
import com.example.moneymanagementjava.constant.ConstantFunction;
import com.example.moneymanagementjava.converter.ConvertDate;
import com.example.moneymanagementjava.database.MoneyExecutors;
import com.example.moneymanagementjava.database.MoneyManagementDatabase;
import com.example.moneymanagementjava.model.CustomSpinnerItem;
import com.example.moneymanagementjava.model.MoneyManagement;
import com.example.moneymanagementjava.ui.addscreen.DatePickerFragmentDialog;
import com.example.moneymanagementjava.ui.overviewscreen.OverviewViewModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MyAccountFragment extends BaseFragment implements CallBackAddFragment,
        AccountAdapter.OnItemMoneyClickListener {

    private TextView totalMoneyMyAccount;
    private RecyclerView recycleViewListMoney;
    private MyAccountViewModel myAccountViewModel;
    private OverviewViewModel overviewViewModel;
    private List<MoneyManagement> moneyManagementList;
    private float totalMoney = 0;
    private final String TAG = "MyAccountFragment";
    private AccountAdapter adapter;
    private MoneyManagement termMoneyManagement;
    private String itemSpinnerName, detailCategory;
    private Spinner spinnerCustomDialog;
    private int spinnerItemId = 0;
    private Button btnCancelMoneyCustomDialog, btnSaveMoneyCustomDialog;
    private TextView txtUpdateDateCustomDialog, txtUpdateContentMoneyType, txtTitle1Item;
    private EditText edtUpdateAmountOfMoney;
    private ImageView imgUpdateDateCustomDialog, imgNavigateUpdateMoneyType;

    @Override
    protected int layoutId() {
        return R.layout.fragment_my_account;
    }

    @Override
    protected BaseViewModel viewModel() {
        DataProviderImpl dataProvider = new DataProviderImpl(MoneyManagementDatabase.getInstance(requireContext()).moneyDao());
        myAccountViewModel = new MyAccountViewModel(dataProvider);
        overviewViewModel = new OverviewViewModel(dataProvider);
        return myAccountViewModel;
    }

    @Override
    protected void initView(View view) {
        overviewViewModel.getAllMoneyManagementList();
        moneyManagementList = new ArrayList<>();
        termMoneyManagement = new MoneyManagement();
        totalMoneyMyAccount = view.findViewById(R.id.totalMoneyMyAccount);
        recycleViewListMoney = view.findViewById(R.id.recycleViewListMoney);

    }

    @Override
    protected void eventView() {
        initTitleForTotalMoney();
        initRecycleview();
    }


    @SuppressLint("SetTextI18n")
    private void initTitleForTotalMoney() {
        overviewViewModel.allMoneyManagementList.observe(this, list -> {
            if (moneyManagementList != null) {
                moneyManagementList.clear();
                totalMoney = 0;
                moneyManagementList.addAll(list);
                for (int i = 0; i < list.size(); i++) {
                    totalMoney += list.get(i).getTotalMoney();
                }
                totalMoneyMyAccount.setText("Total money in my account " + totalMoney + " đ");
                adapter.updateData(list);
            } else {
                totalMoneyMyAccount.setText("Total money in my account " + 0 + " đ");
                Toast.makeText(requireContext(), "Database is empty. Please add data to database", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecycleview() {
        adapter = new AccountAdapter(moneyManagementList, requireContext(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false);
        recycleViewListMoney.setLayoutManager(linearLayoutManager);
        recycleViewListMoney.setAdapter(adapter);
    }

    @Override
    public void popUpMenuValue(String value) {
        if (termMoneyManagement != null) {
            if (value.equals("Delete")) {
                MoneyExecutors.getInstance().diskIO().execute(() -> {
                    myAccountViewModel.deleteMoneyManagement(termMoneyManagement);
                    Log.d(TAG, "run: successfully");
                });
            } else {
                LayoutInflater inflater =
                        (LayoutInflater) requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View view = inflater.inflate(R.layout.custom_dialog_update, null);

                AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());
                alert.setView(view);
                AlertDialog dialog = alert.create();
                dialog.show();
                Log.d(TAG, "popUpMenuValue: update");
                initViewForDialog(view);
                initOriginalValueForDialog();
                initEventForDialog(dialog);
            }
        } else {
            Toast.makeText(requireContext(), "Please wait to add data", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViewForDialog(View view) {
        spinnerCustomDialog = view.findViewById(R.id.spinnerCustomDialog);
        btnCancelMoneyCustomDialog = view.findViewById(R.id.btnCancelMoneyCustomDialog);
        btnSaveMoneyCustomDialog = view.findViewById(R.id.btnSaveMoneyCustomDialog);
        txtUpdateDateCustomDialog = view.findViewById(R.id.txtUpdateDateCustomDialog);
        txtUpdateContentMoneyType = view.findViewById(R.id.txtUpdateContentMoneyType);
        edtUpdateAmountOfMoney = view.findViewById(R.id.edtUpdateAmountOfMoney);
        imgUpdateDateCustomDialog = view.findViewById(R.id.imgUpdateDateCustomDialog);
        imgNavigateUpdateMoneyType = view.findViewById(R.id.imgNavigateUpdateMoneyType);
    }

    @SuppressLint("SetTextI18n")
    private void initOriginalValueForDialog() {
        Log.d(TAG, "initOriginalValueForDialog: termMoneyManagement.getSpinnerItemId()" + termMoneyManagement.getSpinnerItemId());
        spinnerCustomDialog.post(new Runnable() {
            public void run() {
                spinnerCustomDialog.setSelection(termMoneyManagement.getSpinnerItemId());
            }
        });
        txtUpdateContentMoneyType.setText(termMoneyManagement.getDetailCategory());
        txtUpdateDateCustomDialog.setText(termMoneyManagement.getAddedDate().toString());
        edtUpdateAmountOfMoney.setText(termMoneyManagement.getTotalMoney() + "");
    }

    private void initEventForDialog(AlertDialog dialog) {
        setSpinner();
        updateDate();
        updateDetailCategory();
        saveUpdateData(dialog);
        cancelUpdate(dialog);
    }

    private void setSpinner() {
        ArrayList<CustomSpinnerItem> customList = ConstantFunction.getInstance().getCustomlist();
        CustomSpinnerAdapter spinnerAdapter = new CustomSpinnerAdapter(requireContext(), customList);
        if (spinnerCustomDialog != null) {
            spinnerCustomDialog.setAdapter(spinnerAdapter);
            DataBindingCustom.getInstance(this).itemSpinnerSelected(spinnerCustomDialog, requireContext());

        }
    }


    private void updateDate() {
        imgUpdateDateCustomDialog.setOnClickListener(view -> {
            DatePickerFragmentDialog dialog = new DatePickerFragmentDialog(txtUpdateDateCustomDialog);
            dialog.show(requireActivity().getSupportFragmentManager(), "date picker");
        });
    }

    private void updateDetailCategory() {
        imgNavigateUpdateMoneyType.setOnClickListener(v -> {
            ConstantFunction.getInstance().chooseDetailCategory(requireContext(),
                    itemSpinnerName,
                    imgNavigateUpdateMoneyType,
                    this
            );

        });
    }

    private void saveUpdateData(AlertDialog dialog) {
        ConvertDate convertDate = new ConvertDate();
        btnSaveMoneyCustomDialog.setOnClickListener(v ->
        {
            MoneyManagement moneyManagement = new MoneyManagement();
            moneyManagement.setCategory(itemSpinnerName);
            moneyManagement.setTotalMoney(Float.parseFloat(edtUpdateAmountOfMoney.getText().toString()));
            try {
                moneyManagement.setAddedDate(convertDate.convertStringToDate(txtUpdateDateCustomDialog.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            moneyManagement.setDetailCategory(detailCategory);
            MoneyExecutors.getInstance().diskIO().execute(() -> {
                myAccountViewModel.updateMoneyManagement(moneyManagement);
                Log.d(TAG, "run: saveUpdateData successfully");
                dialog.dismiss();
            });
        });

    }

    private void cancelUpdate(AlertDialog dialog) {
        btnCancelMoneyCustomDialog.setOnClickListener(view -> dialog.dismiss());
    }

    @Override
    public void spinnerItemValueObserver(CustomSpinnerItem item) {
        Log.d(TAG, "spinnerItemValueObserver:  item.getId() = " + item.getId());
        spinnerItemId = item.getId();
        itemSpinnerName = item.getSpinnerItemName();
    }


    @Override
    public void onItemMoneyClick(MoneyManagement moneyManagement, int position) {
    }

    @Override
    public void menuOnClick(ImageView imageView, MoneyManagement moneyManagement) {
        DataBindingCustom.getInstance(this).itemPopupMenu(
                requireContext(), imageView, R.menu.menu_option_in_my_acc
        );
        termMoneyManagement = moneyManagement;
    }

}