package com.daohuyen.dell.storeadmin.presenter.product.detail_product;

import android.content.Context;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.common.ResponseCode;
import com.daohuyen.dell.storeadmin.service.retrofit.ApiClient;
import com.daohuyen.dell.storeadmin.service.retrofit.product.ProductService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductDetailInteratorIpl implements ProductDetailInterator {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public ProductDetailInteratorIpl(Context context) {
        this.context = context;
        compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void getProductDetail(String productID, OnGetProductDetailCompleteListener listener) {
        Disposable disposable = ApiClient.getClient().create(ProductService.class)
                .getProductDetail(productID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        response -> {
                            switch (response.code()) {
                                case ResponseCode.OK: {
                                    listener.onGetProductDetailComplete(response.body().getData());
                                    break;
                                }
                                case ResponseCode.NOT_FOUND: {
                                    listener.onMessageEror(response.message());
                                    break;
                                }
                                default: {
                                    listener.onMessageEror(response.message());
                                    break;
                                }

                            }
                        }, error -> {
                            listener.onMessageEror(context.getString(R.string.server_error));
                        });
        compositeDisposable.add(disposable);

    }


    @Override
    public void onViewDestroy() {
        this.compositeDisposable.clear();

    }
}
