package com.example.moneymanagementjava.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        viewModel();
        initView();
        eventView();
    }

    protected abstract int layoutId();
    protected abstract void initView();
    protected abstract void eventView();
    protected  abstract  BaseViewModel viewModel();
}
