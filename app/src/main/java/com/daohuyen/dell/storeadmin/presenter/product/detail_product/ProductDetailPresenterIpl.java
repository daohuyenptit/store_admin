package com.daohuyen.dell.storeadmin.presenter.product.detail_product;

import android.content.Context;

import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
import com.daohuyen.dell.storeadmin.view.product_detail.ProductDetailView;

public class ProductDetailPresenterIpl implements ProductDetailPresenter {
    private Context context;
    private ProductDetailView productDetailView;
    private ProductDetailInterator productDetailInterator;
    int currentpage=0;

    public ProductDetailPresenterIpl(Context context, ProductDetailView productDetailView) {
        this.context = context;
        this.productDetailView = productDetailView;
        this.productDetailInterator=new ProductDetailInteratorIpl(context);
    }

    @Override
    public void fetchProductDetail(String productID) {
        productDetailView.showLoading();
        productDetailInterator.getProductDetail(productID, new OnGetProductDetailCompleteListener() {
            @Override
            public void onGetProductDetailComplete(ProductViewModel productViewModel) {
                productDetailView.showProductDetail(productViewModel);
                productDetailView.hideLoading();
            }

            @Override
            public void onMessageEror(String msg) {
                productDetailView.hideLoading();

            }
        });

    }


    @Override
    public void onViewDestroy() {
        this.productDetailInterator.onViewDestroy();

    }
}
