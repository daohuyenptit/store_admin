package com.daohuyen.dell.storeadmin.view.product.addProduct;

import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;

import java.util.List;

public interface CategoryView {
    void showProgress();
    void hideProgress();
    void loadAllCategories(List<CategoryViewModel> list);
}
