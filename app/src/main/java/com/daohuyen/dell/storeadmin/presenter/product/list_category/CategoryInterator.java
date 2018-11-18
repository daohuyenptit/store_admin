package com.daohuyen.dell.storeadmin.presenter.product.list_category;


import com.daohuyen.dell.storeadmin.presenter.BaseInteractor;

public interface CategoryInterator extends BaseInteractor {
    void getCategory(String productGoupID,OnGetCategorySuccessListener onGetCategorySuccessListener);
}
