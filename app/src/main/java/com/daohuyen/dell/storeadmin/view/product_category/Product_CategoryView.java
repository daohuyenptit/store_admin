package com.daohuyen.dell.storeadmin.view.product_category;

import com.daohuyen.dell.storeadmin.model.PageList;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;


public interface Product_CategoryView {
    void showLoadMoreProgress();
    void hideLoadMoreProgress();
    void enableLoadMore(boolean enable);
    void enableRefreshing(boolean enable);
    void showRefreshingProgress();
    void hideRefreshingProgress();
    void addProductPreviews(PageList<ProductViewModel> productViewModelPageList);
    void refreshProductPreview(PageList<ProductViewModel> productViewModelPageList);

}
