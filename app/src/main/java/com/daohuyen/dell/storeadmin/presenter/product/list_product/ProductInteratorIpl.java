package com.daohuyen.dell.storeadmin.presenter.product.list_product;
import android.content.Context;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.common.ResponseCode;
import com.daohuyen.dell.storeadmin.model.PageList;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
import com.daohuyen.dell.storeadmin.service.retrofit.ApiClient;
import com.daohuyen.dell.storeadmin.service.retrofit.product.ProductService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductInteratorIpl implements ProductInterator {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public ProductInteratorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }



    @Override
    public void onViewDestroy() {
        compositeDisposable.clear();
    }

    @Override
    public void getProduct(String categoryID, int pageIndex, int pageSize, OnGetProducSuccessListener listener) {
        Disposable disposable = ApiClient.getClient().create(ProductService.class)
                .getAllProductByCategoryID(categoryID,pageIndex, pageSize,null, null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                    switch (response.code()) {
                        case ResponseCode.OK: {
                            listener.onGetPageProductPreviewsSuccess((PageList<ProductViewModel>) response.body().getData());
                            break;
                        }
                        case ResponseCode.NOT_FOUND: {
                            listener.onMessageEror(response.message());
                            break;
                        }
                        default:{
                            listener.onMessageEror(response.message());
                            break;
                        }
                    }
                }, error -> {
                    listener.onMessageEror(context.getString(R.string.server_error));
                });
        compositeDisposable.add(disposable);

    }



    }



