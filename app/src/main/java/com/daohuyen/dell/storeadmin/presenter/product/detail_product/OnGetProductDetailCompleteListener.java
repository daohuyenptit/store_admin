package com.daohuyen.dell.storeadmin.presenter.product.detail_product;


import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;

public  interface OnGetProductDetailCompleteListener {
    void onGetProductDetailComplete(ProductViewModel productViewModel);
    void onMessageEror(String msg);
}
