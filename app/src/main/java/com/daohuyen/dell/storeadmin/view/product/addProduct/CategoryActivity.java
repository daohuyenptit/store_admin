package com.daohuyen.dell.storeadmin.view.product.addProduct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.adapter.CategoryAdaper;
import com.daohuyen.dell.storeadmin.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.storeadmin.common.Constants;
import com.daohuyen.dell.storeadmin.custom.LoadingDialog;
import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;
import com.daohuyen.dell.storeadmin.presenter.product.list_category.CategoryPresenter;
import com.daohuyen.dell.storeadmin.presenter.product.list_category.CategoryPresenterImpl;
import com.daohuyen.dell.storeadmin.service.eventbus.SendCategory;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity implements CategoryView,RecyclerViewAdapter.OnItemClickListener {
    CategoryPresenter presenter;
    LoadingDialog loadingDialog;
    CategoryAdaper adaper;
    @BindView(R.id.rc_category)
    RecyclerView rc_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Thêm sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loadingDialog=new LoadingDialog(this);
        adaper=new CategoryAdaper(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rc_category.setLayoutManager(linearLayoutManager);
        rc_category.setAdapter(adaper);
        presenter=new CategoryPresenterImpl(this,this);
        presenter.loadAllCategory(getIntent().getStringExtra(Constants.GROUPID));
        adaper.addOnItemClickListener(this);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showProgress() {
        loadingDialog.show();


    }

    @Override
    public void hideProgress() {
        loadingDialog.hide();

    }

    @Override
    public void loadAllCategories(List<CategoryViewModel> list) {
        adaper.addModels(list,false);


    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {
        CategoryViewModel categoryViewModel=this.adaper.getItem(position,CategoryViewModel.class);
        SendCategory sendCategory=new SendCategory();
        sendCategory.setCategoryViewModel(categoryViewModel);
        EventBus.getDefault().post(sendCategory);
        finish();

    }
}
