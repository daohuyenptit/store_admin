package com.daohuyen.dell.storeadmin.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.view.fragment.statistics.allbestseller.AllFragment;
import com.daohuyen.dell.storeadmin.view.fragment.statistics.daybestSeller.DayFragment;
import com.daohuyen.dell.storeadmin.view.fragment.statistics.inventory.InventorProductFragment;
import com.daohuyen.dell.storeadmin.view.fragment.statistics.monthbestseller.MonthFragment;

public class ViewPageStatisticsAdapter extends FragmentStatePagerAdapter {
    private Fragment[] fragments;

    public ViewPageStatisticsAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[4];
        fragments[0] = new AllFragment();
        fragments[1]=new MonthFragment();
        fragments[2] = new DayFragment();
        fragments[3] = new InventorProductFragment();



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
                return R.id.nav_all;
            }

            case 1: {
                return R.id.nav_month;
            }
            case 2: {
                return R.id.nav_day;
            }case 3: {
                return R.id.nav_inventory;
            }

            default: {
                return R.id.nav_inventory;
            }
        }
    }
}
