package com.example.moneymanagementjava.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private FragmentManager fragmentManager;
    private Lifecycle lifecycle;

    public ViewPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager,lifecycle);
        this.fragmentManager = fragmentManager;
        this.lifecycle = lifecycle;
    }
    private ArrayList<Fragment>  fragmentList  = new ArrayList<>();

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }
}
