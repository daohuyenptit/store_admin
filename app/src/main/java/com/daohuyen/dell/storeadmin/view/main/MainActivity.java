package com.daohuyen.dell.storeadmin.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.daohuyen.dell.storeadmin.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.storeadmin.common.BottomNavigationViewHelper;
import com.daohuyen.dell.storeadmin.common.Constants;
import com.daohuyen.dell.storeadmin.common.Utils;
import com.daohuyen.dell.storeadmin.common.ViewPageAdapter;
import com.daohuyen.dell.storeadmin.custom.LoadingDialog;
import com.daohuyen.dell.storeadmin.view.fragment.historyorder.HistoryActivity;
import com.daohuyen.dell.storeadmin.view.login.LoginActivity;
import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.view.product.addProduct.AddProductActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,
        BottomNavigationView.OnNavigationItemSelectedListener,RecyclerViewAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener{
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;
    ViewPager viewPager;
    private LoadingDialog loadingDialog;
    View userHeaderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.view_pager);
        final ViewPageAdapter viewPageAdapter= new ViewPageAdapter(getSupportFragmentManager());
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
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView= findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        switchNavigationDrawer(Constants.LOGIN_TRUE.equals(Utils.getSharePreferenceValues(this,Constants.STATUS_LOGIN)));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id==R.id.mn_add){
            startActivity(new Intent(this, AddProductActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_order: {
                startActivity(new Intent(this, HistoryActivity.class));
                //
                break;
            }
            case R.id.nav_add: {
                startActivity(new Intent(this, AddProductActivity.class));
                //
                break;
            }
            case R.id.nav_edit: {
                //
                break;
            }
            case R.id.nav_delete: {
                //
                break;
            }
            case R.id.nav_logout: {
                Utils.setSharePreferenceValues(this, Constants.STATUS_LOGIN, Constants.LOGIN_FAIL);
//                Utils.saveHeaderProfile(this, null);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                //
                break;
            }
            case R.id.nav_statistics:{
                startActivity(new Intent(this, StatisticsActivity.class));
                break;
            }
            case R.id.nav_login: {
                startActivity(new Intent(this, LoginActivity.class));
                //
                break;
            }

            case R.id.nav_home: {
                viewPager.setCurrentItem(0);
                break;
            }
            case R.id.nav_search: {
                viewPager.setCurrentItem(1);
                break;
            }
            case R.id.nav_inform: {
                viewPager.setCurrentItem(2);
                break;
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void switchNavigationDrawer(boolean isLoggedIn) {
        navigationView.getMenu().clear();
        navigationView.removeHeaderView(userHeaderView);
        if (isLoggedIn) {
            Log.i("login","thanh cong");

            userHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_auth);
            navigationView.inflateMenu(R.menu.menu_navigation_login);
            Utils.setSharePreferenceValues(this, Constants.STATUS_LOGIN, Constants.LOGIN_TRUE);

        } else {
            Log.i("a"," k thanh cong");
            userHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_no_auth);
            navigationView.inflateMenu(R.menu.menu_navigation_logout);
            Utils.setSharePreferenceValues(this, Constants.STATUS_LOGIN, Constants.LOGIN_FAIL);
        }
    }
    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {

    }
}
