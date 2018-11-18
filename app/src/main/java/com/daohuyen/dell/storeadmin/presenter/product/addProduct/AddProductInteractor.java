package com.daohuyen.dell.storeadmin.presenter.product.addProduct;

import com.daohuyen.dell.storeadmin.model.body.ProductBody;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
import com.daohuyen.dell.storeadmin.presenter.BaseInteractor;
public interface AddProductInteractor extends BaseInteractor {
    void addProduct(String categoryID, ProductBody productViewModel, OnGetAddSuccess listener);
}
