package com.daohuyen.dell.storeadmin.presenter.product.detail_product;


import com.daohuyen.dell.storeadmin.presenter.BaseInteractor;

public interface ProductDetailInterator extends BaseInteractor {
    void getProductDetail(String productID, OnGetProductDetailCompleteListener listener);
}
