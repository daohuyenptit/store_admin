package com.daohuyen.dell.storeadmin.presenter.product.addProduct;

import android.net.Uri;

import com.daohuyen.dell.storeadmin.model.body.ProductBody;
import com.daohuyen.dell.storeadmin.presenter.BasePresenter;

public interface AddProductPresenter extends BasePresenter {
    void addProduct(String categoryID, Uri image, ProductBody productBody);
}
