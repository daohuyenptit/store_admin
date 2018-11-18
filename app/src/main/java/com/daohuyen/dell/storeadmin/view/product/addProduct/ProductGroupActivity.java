package com.daohuyen.dell.storeadmin.view.product.addProduct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.adapter.ProductGroupAdapter;
import com.daohuyen.dell.storeadmin.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.storeadmin.common.Constants;
import com.daohuyen.dell.storeadmin.model.Product;
import com.daohuyen.dell.storeadmin.model.response.ProductGroup;
import com.daohuyen.dell.storeadmin.presenter.product.list_category.CategoryPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductGroupActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener {
    @BindView(R.id.rc_group)
    RecyclerView rc_group;
    ProductGroupAdapter adapter;
    ArrayList<ProductGroup> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_group);
        ButterKnife.bind(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Thêm sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        list.add(new ProductGroup("1","Mỹ phẩm trang điểm"));
        list.add(new ProductGroup("2","Mỹ phẩm dưỡng da"));
        list.add(new ProductGroup("3","Nước hoa"));
        adapter=new ProductGroupAdapter(this);
        adapter.addModels(list,false);
        adapter.addOnItemClickListener(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rc_group.setLayoutManager(linearLayoutManager);
        rc_group.setAdapter(adapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {
        ProductGroup productGroup=this.adapter.getItem(position, ProductGroup.class);
        Intent intent=new Intent(this,CategoryActivity.class);
        intent.putExtra(Constants.GROUPID,productGroup.getId());
        startActivity(intent);
        finish();

    }
}
