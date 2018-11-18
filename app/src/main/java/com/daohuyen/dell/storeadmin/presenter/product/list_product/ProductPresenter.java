package com.daohuyen.dell.storeadmin.presenter.product.list_product;


import com.daohuyen.dell.storeadmin.presenter.BasePresenter;

public interface ProductPresenter extends BasePresenter {
    void loadMoreProductPreviews(String categoryID);
    void refreshProductPreviews(String categoryID);

}
