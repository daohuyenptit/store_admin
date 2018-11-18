package com.daohuyen.dell.storeadmin.presenter.statistics.inventory;

import android.content.Context;
import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.common.ToastUtils;
import com.daohuyen.dell.storeadmin.model.PageList;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
import com.daohuyen.dell.storeadmin.view.fragment.statistics.inventory.InteratoryView;

public class InventoryPresenterIpl implements InventoryPresenter {
    private Context context;
    private InteratoryView product_categoryView;
    private InventoryInterator inventoryInterator;
    private int currentpage=0;

    public InventoryPresenterIpl(Context context, InteratoryView product_categoryView) {
        this.context = context;
        this.product_categoryView = product_categoryView;
        this.inventoryInterator =new InventoryInteratorIpl(context);
    }



    @Override
    public void onViewDestroy() {
        inventoryInterator.onViewDestroy();

    }



    @Override
    public void loadMoreProductPreviews(String date) {
        product_categoryView.showLoadMoreProgress();
        product_categoryView.enableLoadMore(true);
        inventoryInterator.getProduct(date, currentpage + 1, 100, new OnGetProducSuccessListener() {
            @Override
            public void onGetPageProductPreviewsSuccess(PageList<ProductViewModel> productViewModelPageList) {
                currentpage++;
                product_categoryView.hideLoadMoreProgress();
                if (productViewModelPageList.getPageIndex() == productViewModelPageList.getTotalPage() - 1) {
                    product_categoryView.enableLoadMore(false);
                }
                product_categoryView.addProductPreviews(productViewModelPageList);


            }



            @Override
            public void onMessageEror(String msg) {
                product_categoryView.hideLoadMoreProgress();
                ToastUtils.quickToast(context, R.string.error_message);

            }
        });


    }

    @Override
    public void refreshProductPreviews(String date) {
        product_categoryView.enableLoadMore(false);
        inventoryInterator.getProduct(date, 0, 100, new OnGetProducSuccessListener() {
            @Override
            public void onGetPageProductPreviewsSuccess(PageList<ProductViewModel> productViewModelPageList) {
                currentpage=0;
                if (productViewModelPageList.getPageIndex() == productViewModelPageList.getTotalPage() - 1) {
                    product_categoryView.enableLoadMore(false);
                }
                product_categoryView.enableLoadMore(true);
                product_categoryView.refreshProductPreview(productViewModelPageList);

            }



            @Override
            public void onMessageEror(String msg) {
                product_categoryView.hideLoadMoreProgress();
                product_categoryView.enableLoadMore(true);
                ToastUtils.quickToast(context, R.string.error_message);

            }
        });

    }



}
