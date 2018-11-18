package com.daohuyen.dell.storeadmin.presenter.product.editProduct;

import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;

public interface OnGetEditSuccess {
    void onSuccess(ProductViewModel productViewModel);
    void onError(String message);

}
