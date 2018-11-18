package com.daohuyen.dell.storeadmin.presenter.product.category;

import com.daohuyen.dell.storeadmin.presenter.BaseInteractor;

public interface CateInterator extends BaseInteractor {
    void getCategory(OnGetCategorySuccessListener onGetCategorySuccessListener);
}
