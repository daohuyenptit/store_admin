package com.daohuyen.dell.storeadmin.view.product_category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.adapter.EndlessLoadingRecyclerViewAdapter;
import com.daohuyen.dell.storeadmin.adapter.ProductAdapter;
import com.daohuyen.dell.storeadmin.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.storeadmin.common.Constants;
import com.daohuyen.dell.storeadmin.model.PageList;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
import com.daohuyen.dell.storeadmin.presenter.product.list_product.ProductPresenter;
import com.daohuyen.dell.storeadmin.presenter.product.list_product.ProductPresenterIpl;
import com.daohuyen.dell.storeadmin.view.product_detail.ProductDetailActivity;

import java.util.List;


public class Product_CateoryActivity extends AppCompatActivity implements  RecyclerViewAdapter.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener,
        EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener,Product_CategoryView {
    String categoryID;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView mRecycleView;
    ProductPresenter presenter;
    ProductAdapter adapter;
    List<ProductViewModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__cateory);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Các sản phẩm danh mục");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mRecycleView=findViewById(R.id.recycler_product_category);
        swipeRefreshLayout= findViewById(R.id.swipe_refresh);
        categoryID= getIntent().getStringExtra(Constants.CATEGORY_ID);

        presenter=new ProductPresenterIpl(this,this);
        adapter = new ProductAdapter(this);
        adapter.addOnItemClickListener(this);
        adapter.setLoadingMoreListener(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecycleView.setLayoutManager(layoutManager);
//        mRecycleView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryLight, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(this);

        presenter.refreshProductPreviews(categoryID);
    }

    @Override
    public void showLoadMoreProgress() {
        adapter.showLoadingItem(true);
    }

    @Override
    public void hideLoadMoreProgress() {
        adapter.hideLoadingItem();
    }

    @Override
    public void enableLoadMore(boolean enable) {
        adapter.enableLoadingMore(enable);
    }

    @Override
    public void enableRefreshing(boolean enable) {
        swipeRefreshLayout.setEnabled(enable);
    }

    @Override
    public void showRefreshingProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefreshingProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addProductPreviews(PageList<ProductViewModel> productViewModelPageList) {

        adapter.addModels(productViewModelPageList.getResults(),false);

    }

    @Override
    public void refreshProductPreview(PageList<ProductViewModel> productViewModelPageList) {
        adapter.refresh(productViewModelPageList.getResults());
//        this.list = productViewModelPageList.getResults();
    }



    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {

        ProductViewModel productViewModel = this.adapter.getItem(position, ProductViewModel.class);
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(Constants.KEY_PRODUCT_ID, productViewModel.getId());
        intent.putExtra(Constants.CATEGORY_NAME,getIntent().getStringExtra(Constants.CATEGORY_NAME));
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRefresh() {
        presenter.refreshProductPreviews(categoryID);

    }

    @Override
    public void onLoadMore() {
        presenter.loadMoreProductPreviews(categoryID);

    }
}
