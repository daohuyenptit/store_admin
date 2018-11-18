package com.daohuyen.dell.storeadmin.presenter.product.editProduct;

import com.daohuyen.dell.storeadmin.model.body.ProductBody;
import com.daohuyen.dell.storeadmin.presenter.BaseInteractor;

public interface EditProductInteractor extends BaseInteractor {
    void editProduct(String productID, ProductBody productViewModel, OnGetEditSuccess listener);
}
