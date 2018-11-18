package com.daohuyen.dell.storeadmin.presenter.product.list_category;
import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;

import java.util.List;

public interface OnGetCategorySuccessListener {
    void onSuccess(List<CategoryViewModel> categories);
    void onError(String msg);
}
