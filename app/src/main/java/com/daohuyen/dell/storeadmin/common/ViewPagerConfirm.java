package com.daohuyen.dell.storeadmin.common;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.daohuyen.dell.storeadmin.view.fragment.historyorder.sending.SendingFragment;
import com.daohuyen.dell.storeadmin.view.fragment.historyorder.sent.SentFragment;
import com.daohuyen.dell.storeadmin.view.fragment.historyorder.waitconfirm.WaitConfirmFragment;

public class ViewPagerConfirm extends FragmentStatePagerAdapter {
    private Fragment[] fragments;
    public ViewPagerConfirm(FragmentManager fm) {
        super(fm);
        fragments= new Fragment[3];
        fragments[0]= new WaitConfirmFragment();
        fragments[1]= new SendingFragment();
        fragments[2]= new SentFragment();
    }

    public Fragment[] getFragments() {
        return fragments;
    }

    public void setFragments(Fragment[] fragments) {
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Chờ xác nhận";
                break;
            case 1:
                title = "Đang giao";
                break;
            case 2:
                title = "Đã giao";
                break;
        }
        return title;
    }
}
