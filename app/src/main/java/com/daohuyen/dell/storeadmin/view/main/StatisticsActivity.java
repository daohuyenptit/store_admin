package com.daohuyen.dell.storeadmin.view.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.common.BottomNavigationViewHelper;
import com.daohuyen.dell.storeadmin.common.ViewPageStatisticsAdapter;
import com.daohuyen.dell.storeadmin.custom.LoadingDialog;

public class StatisticsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        final ViewPageStatisticsAdapter viewPageAdapter= new ViewPageStatisticsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.setSelectedItemId(viewPageAdapter.getMenuIDByPosition(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        loadingDialog = new LoadingDialog(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_all: {
                viewPager.setCurrentItem(0);
                break;
            }
            case R.id.nav_month: {
                viewPager.setCurrentItem(1);
                break;
            }
            case R.id.nav_day: {
                viewPager.setCurrentItem(2);
                break;
            }
            case R.id.nav_inventory: {
                viewPager.setCurrentItem(3);
                break;
            }
        }
        return true;
    }
}
