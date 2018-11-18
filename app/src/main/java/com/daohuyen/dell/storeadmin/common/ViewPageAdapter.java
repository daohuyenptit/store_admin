package com.daohuyen.dell.storeadmin.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.view.fragment.editProduct.CategoryFragment;
import com.daohuyen.dell.storeadmin.view.fragment.historyorder.HomeFragment;

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    private Fragment[] fragments;

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[3];
        fragments[0] = new HomeFragment();
        fragments[1] = new CategoryFragment();
        fragments[2] = new HomeFragment();

    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    public static int getMenuIDByPosition(int position) {
        switch (position) {
            case 0: {
                return R.id.nav_home;
            }

            case 1: {
                return R.id.nav_search;
            }
            case 2: {
                return R.id.nav_inform;
            }
            default: {
                return R.id.nav_home;
            }
        }
    }
}

