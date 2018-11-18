package com.daohuyen.dell.storeadmin.presenter.product.list_product;


import com.daohuyen.dell.storeadmin.model.PageList;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
public interface OnGetProducSuccessListener {
    void onGetPageProductPreviewsSuccess(PageList<ProductViewModel> list);
    void onMessageEror(String msg);
}
