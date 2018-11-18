package com.daohuyen.dell.storeadmin.presenter.product.list_product;


import com.daohuyen.dell.storeadmin.presenter.BaseInteractor;

public interface ProductInterator extends BaseInteractor {
    void getProduct(String categoryID, int pageIndex, int pageSize, OnGetProducSuccessListener onGetProducSuccessListener);
}
