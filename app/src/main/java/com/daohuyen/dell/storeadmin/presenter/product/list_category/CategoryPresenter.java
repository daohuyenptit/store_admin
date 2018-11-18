package com.daohuyen.dell.storeadmin.presenter.product.list_category;


import com.daohuyen.dell.storeadmin.presenter.BasePresenter;

public interface CategoryPresenter extends BasePresenter {
    void loadAllCategory(String productGroupID);
}
