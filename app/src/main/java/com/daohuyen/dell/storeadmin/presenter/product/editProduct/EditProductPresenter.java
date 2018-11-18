package com.daohuyen.dell.storeadmin.presenter.product.editProduct;

import android.net.Uri;

import com.daohuyen.dell.storeadmin.model.body.ProductBody;
import com.daohuyen.dell.storeadmin.presenter.BasePresenter;

public interface EditProductPresenter extends BasePresenter {
    void editProduct(String productID, Uri filePath, ProductBody productBody);
}
