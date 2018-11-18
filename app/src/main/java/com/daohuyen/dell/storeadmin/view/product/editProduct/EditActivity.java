package com.daohuyen.dell.storeadmin.view.product.editProduct;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.adapter.ProductGroupAdapter;
import com.daohuyen.dell.storeadmin.common.Constants;
import com.daohuyen.dell.storeadmin.common.Utils;
import com.daohuyen.dell.storeadmin.custom.LoadingDialog;
import com.daohuyen.dell.storeadmin.model.body.ProductBody;
import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
import com.daohuyen.dell.storeadmin.presenter.product.addProduct.AddProductPresenter;
import com.daohuyen.dell.storeadmin.presenter.product.addProduct.AddProductPresenterIpl;
import com.daohuyen.dell.storeadmin.presenter.product.editProduct.EditProductPresenter;
import com.daohuyen.dell.storeadmin.presenter.product.editProduct.EditProductPresenterIpl;
import com.daohuyen.dell.storeadmin.service.eventbus.SendCategory;
import com.daohuyen.dell.storeadmin.view.product.addProduct.AddProductActivity;
import com.daohuyen.dell.storeadmin.view.product.addProduct.ProductGroupActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditActivity extends AppCompatActivity implements EditProductView,View.OnClickListener{
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.img_production)
    ImageView img_product;
    @BindView(R.id.et_des)
    EditText et_des;
    @BindView(R.id.lnGroup)
    LinearLayout lnGroup;
    @BindView(R.id.et_category)
    EditText et_category;
    @BindView(R.id.et_price)
    EditText et_price;
    @BindView(R.id.bt_edit)
    Button bt_edit;
    ProductGroupAdapter adapter;
    EditProductPresenter productPresenter;
    LoadingDialog loadingDialog;
    CategoryViewModel categoryViewModel;
    ProductViewModel productViewModel;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Sửa sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        loadingDialog=new LoadingDialog(this);
        lnGroup.setOnClickListener(this);
        bt_edit.setOnClickListener(this);
        EventBus.getDefault().register(this);
        productPresenter=new EditProductPresenterIpl(this,this);
        productViewModel= (ProductViewModel) getIntent().getSerializableExtra(Constants.PRODUCT);
        Glide.with(this)
                .load(productViewModel.getLogoUrl())
                .apply(new RequestOptions().placeholder(R.drawable.br_account)).into(img_product);
        et_name.setText(productViewModel.getName());
        et_des.setText(productViewModel.getDes());
        et_category.setText(getIntent().getStringExtra(Constants.CATEGORY_NAME));
        et_price.setText(productViewModel.getPrice()+"");
        registerForContextMenu(img_product);



    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_image, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.camera:
                break;
            case R.id.storage:
                chooseImage();
                break;
        }
        return super.onContextItemSelected(item);


    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), Constants.PICK_IMAGE_REQUEST_EDIT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constants.PICK_IMAGE_REQUEST_EDIT && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            Glide.with(this)
                    .load(filePath.toString())
                    .apply(new RequestOptions().placeholder(R.drawable.br_account)).into(img_product);
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                img_product.setImageBitmap(bitmap);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onDestroy() {
        //  Log.i(TAG, "onDestroy: ");
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeCategory(SendCategory sendCategory){
        categoryViewModel=sendCategory.getCategoryViewModel();
        et_category.setText(categoryViewModel.getTitle());
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
    public void backToHomeScreen() {
        finish();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lnGroup:
                startActivity(new Intent(this,ProductGroupActivity.class));
                break;
            case R.id.bt_edit:
                ProductBody productBody=new ProductBody();
                productBody.setName(et_name.getText().toString());
                productBody.setDescription(et_des.getText().toString());
                productBody.setPrice(Integer.parseInt(et_price.getText().toString()));
                productPresenter.editProduct(productViewModel.getId(),filePath,productBody);
                break;
        }

    }
}
