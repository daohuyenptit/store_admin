package com.daohuyen.dell.storeadmin.view.fragment.statistics.inventory;

import com.daohuyen.dell.storeadmin.model.PageList;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
public interface InteratoryView {
    void showLoadMoreProgress();
    void hideLoadMoreProgress();
    void enableLoadMore(boolean enable);
    void addProductPreviews(PageList<ProductViewModel> productViewModelPageList);
    void refreshProductPreview(PageList<ProductViewModel> productViewModelPageList);
}
