package com.daohuyen.dell.storeadmin.service.eventbus;

import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;

public class SendProduct {
    private ProductViewModel productViewModel;

    public SendProduct() {
    }

    public SendProduct(ProductViewModel productViewModel) {
        this.productViewModel = productViewModel;
    }

    public ProductViewModel getProductViewModel() {
        return productViewModel;
    }

    public void setProductViewModel(ProductViewModel productViewModel) {
        this.productViewModel = productViewModel;
    }
}
