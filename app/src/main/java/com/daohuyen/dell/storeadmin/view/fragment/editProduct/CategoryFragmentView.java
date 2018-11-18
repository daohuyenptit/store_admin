package com.daohuyen.dell.storeadmin.view.fragment.editProduct;


import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;


import java.util.List;

public interface CategoryFragmentView {
    void showLoading();
    void hideLoading();
    void loadAllCategories(List<CategoryViewModel> list);
}
