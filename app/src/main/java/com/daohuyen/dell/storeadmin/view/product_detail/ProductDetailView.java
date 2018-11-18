package com.daohuyen.dell.storeadmin.view.product_detail;


import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;

public interface ProductDetailView {
    void showLoading();
    void hideLoading();
    void showProductDetail(ProductViewModel productViewModel);

}
