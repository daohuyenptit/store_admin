package com.daohuyen.dell.storeadmin.presenter.product.addProduct;

import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;

public interface OnGetAddSuccess {
    void onSuccess();
    void onError(String message);

}
