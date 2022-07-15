package com.example.moneymanagementjava.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.moneymanagementjava.R;
import com.example.moneymanagementjava.adapter.ViewPagerAdapter;
import com.example.moneymanagementjava.addscreen.AddFragment;
import com.example.moneymanagementjava.base.BaseActivity;
import com.example.moneymanagementjava.base.BaseViewModel;
import com.example.moneymanagementjava.databinding.ActivityMainBinding;
import com.example.moneymanagementjava.otherscreen.OtherFragment;
import com.example.moneymanagementjava.overviewscreen.OverviewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends BaseActivity {
    private ViewPager2 viewPager2;
    private ActivityMainBinding binding;
    private ViewPagerAdapter viewPagerAdapter;
    private OverviewFragment overviewFragment;
    private AddFragment addFragment;
    private OtherFragment otherFragment;

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        viewPager2 = findViewById(R.id.viewPagerMainActivity);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        addFragment = new AddFragment();
        otherFragment = new OtherFragment();
        overviewFragment = new OverviewFragment();
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());


    }

    @Override
    protected BaseViewModel viewModel() {
        return null;
    }


    @Override
    protected void eventView() {
        setViewPager();
        setBottomNavigationView();
    }

    private void setViewPager() {
        viewPagerAdapter.addFragment(overviewFragment);
        viewPagerAdapter.addFragment(addFragment);
        viewPagerAdapter.addFragment(otherFragment);
        binding.viewPagerMainActivity.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.viewPagerMainActivity.setAdapter(viewPagerAdapter);
        binding.viewPagerMainActivity.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {

                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);
                        switch (position) {
                            case 0: {
                                binding.bottomNavigationMainAct.getMenu().findItem(R.id.navigation_overview).setChecked(true);
                                break;
                            }
                            case 1: {
                                binding.bottomNavigationMainAct.getMenu().findItem(R.id.navigation_add).setChecked(true);
                                break;
                            }
                            default: {
                                binding.bottomNavigationMainAct.getMenu().findItem(R.id.navigation_others).setChecked(true);
                                break;
                            }
                        }
                    }

                }
        );
    }


    private void setBottomNavigationView() {
        binding.bottomNavigationMainAct.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_overview) {
                new OverviewFragment();
                binding.viewPagerMainActivity.setCurrentItem(0);
                return true;
            } else if (item.getItemId() == R.id.navigation_add) {
                new AddFragment();
                binding.viewPagerMainActivity.setCurrentItem(1);
                return true;
            } else {
                new OtherFragment();
                binding.viewPagerMainActivity.setCurrentItem(3);
            }
            return true;
        });

    }


}