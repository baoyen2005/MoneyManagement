package com.example.moneymanagementjava.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneymanagementjava.R;
import com.example.moneymanagementjava.model.MoneyManagement;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {
    private List<MoneyManagement> moneyManagementList;
    private Context context;
    private final OnItemMoneyClickListener onItemMoneyClickListener;

    public AccountAdapter(List<MoneyManagement> moneyManagementList, Context context, OnItemMoneyClickListener onItemMoneyClickListener) {
        this.moneyManagementList = moneyManagementList;
        this.context = context;
        this.onItemMoneyClickListener = onItemMoneyClickListener;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mcontext = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        View view = layoutInflater.inflate(R.layout.item_layout_recycle_money_management,parent, false);
        AccountViewHolder viewHolder = new AccountViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        MoneyManagement moneyManagement = moneyManagementList.get(position);

        holder.txtTotalMoneyItem.setText(moneyManagement.getTotalMoney()+"");
        holder.imgShowMenuItem.setImageResource(R.drawable.ic_baseline_more_vert_24);
        holder.txtTitle1Item.setText(moneyManagement.getCategory() +": "+moneyManagement.getAddedDate().toString());
        holder.constraintItemMoney.setOnClickListener(view ->
                onItemMoneyClickListener.onItemMoneyClick(moneyManagement,position));
        holder.imgShowMenuItem.setOnClickListener(view
                ->onItemMoneyClickListener.menuOnClick(holder.imgShowMenuItem, moneyManagement));
    }

    @Override
    public int getItemCount() {
        return moneyManagementList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<MoneyManagement> newMoneyManagementList) {
        moneyManagementList.clear();
        moneyManagementList.addAll(newMoneyManagementList);
        notifyDataSetChanged();
    }

    public interface OnItemMoneyClickListener{
        void onItemMoneyClick(MoneyManagement post, int position);
        void menuOnClick(ImageView imageView, MoneyManagement moneyManagement);
    }

    protected static class AccountViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgShowMenuItem;
        public TextView txtTotalMoneyItem, txtTitle1Item;
        public ConstraintLayout constraintItemMoney;
        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTotalMoneyItem =itemView.findViewById(R.id.txtTotalMoneyItem);
            imgShowMenuItem =itemView.findViewById(R.id.imgShowMenuItem);
            txtTitle1Item = itemView.findViewById(R.id.txtTitle1Item);
            constraintItemMoney = itemView.findViewById(R.id.constraintItemMoney);
        }
    }
}
