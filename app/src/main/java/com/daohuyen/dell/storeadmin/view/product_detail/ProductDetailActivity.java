package com.daohuyen.dell.storeadmin.view.product_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daohuyen.dell.storeadmin.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daohuyen.dell.storeadmin.common.Constants;
import com.daohuyen.dell.storeadmin.common.Utils;
import com.daohuyen.dell.storeadmin.custom.LoadingDialog;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
import com.daohuyen.dell.storeadmin.presenter.product.detail_product.ProductDetailPresenter;
import com.daohuyen.dell.storeadmin.presenter.product.detail_product.ProductDetailPresenterIpl;
import com.daohuyen.dell.storeadmin.service.eventbus.SendCategory;
import com.daohuyen.dell.storeadmin.service.eventbus.SendProduct;
import com.daohuyen.dell.storeadmin.view.product.editProduct.EditActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends AppCompatActivity implements
        ProductDetailView,
        View.OnClickListener
       {


    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.img_product_detail)
    ImageView img_productdetail;
    @BindView(R.id.img_product_background)
    ImageView img_product_background;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_name_product)
    TextView tvNameProduct;
    @BindView(R.id.tv_cost_product)
    TextView tvCostProduct;
    @BindView(R.id.tv_detail_product)
    TextView tvDescription;
    @BindView(R.id.bt_edit)
    Button bt_edit;
    ProductViewModel productViewModel;
    ProductDetailPresenter presenter;
    String productID,categoryID;
    LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Chi tiết sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initVariables();
        EventBus.getDefault().register(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        nestedScrollView.scrollTo(-1, -1);
        nestedScrollView.smoothScrollTo(0, 0);
    }
           @Override
           protected void onDestroy() {
               //  Log.i(TAG, "onDestroy: ");
               super.onDestroy();

               EventBus.getDefault().unregister(this);
           }
           @Subscribe(threadMode = ThreadMode.MAIN)
           public void onChangeProduct(SendProduct sendProduct){
               productViewModel=sendProduct.getProductViewModel();
               Glide.with(this).load(productViewModel.getLogoUrl()).apply(new RequestOptions().placeholder(R.drawable.br_account)).into(img_productdetail);
               Glide.with(this).load(productViewModel.getLogoUrl()).apply(new RequestOptions().placeholder(R.drawable.br_account)).into(img_product_background);
               tvNameProduct.setText(productViewModel.getName());
               tvCostProduct.setText(Utils.formatNumberMoney(productViewModel.getPrice()) + " Đ");
               tvDescription.setText(productViewModel.getDes());
           }

    private void initVariables() {
        ButterKnife.bind(this);
        loadingDialog=new LoadingDialog(this);
        presenter=new ProductDetailPresenterIpl(this,this);
        nestedScrollView.scrollTo(-1, -1);
        nestedScrollView.smoothScrollTo(0, 0);

//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
//            actionBar.setTitle(R.string.clothes_detail);
//        }
        bt_edit.setOnClickListener(this);
        productID = getIntent().getStringExtra(Constants.KEY_PRODUCT_ID);
        categoryID=getIntent().getStringExtra(Constants.CATEGORY_ID);
        if (productID != null) {
            presenter.fetchProductDetail(productID);

        }
        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductDetailActivity.this, EditActivity.class);
                intent.putExtra(Constants.PRODUCT,productViewModel);
                intent.putExtra(Constants.CATEGORY_NAME,getIntent().getStringExtra(Constants.CATEGORY_NAME));
                startActivity(intent);

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {


    }



    @Override
    public void showLoading() {
        loadingDialog.show();

    }

    @Override
    public void hideLoading() {
        loadingDialog.hide();

    }

    @Override
    public void showProductDetail(ProductViewModel productViewModel) {
        this.productViewModel=productViewModel;
        Glide.with(this).load(productViewModel.getLogoUrl()).apply(new RequestOptions().placeholder(R.drawable.br_account)).into(img_productdetail);
        Glide.with(this).load(productViewModel.getLogoUrl()).apply(new RequestOptions().placeholder(R.drawable.br_account)).into(img_product_background);
        tvNameProduct.setText(productViewModel.getName());
        tvCostProduct.setText(Utils.formatNumberMoney(productViewModel.getPrice()) + " Đ");
        tvDescription.setText(productViewModel.getDes());


    }


}
