package com.daohuyen.dell.storeadmin.presenter.statistics.inventory;

import android.content.Context;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.common.ResponseCode;
import com.daohuyen.dell.storeadmin.model.PageList;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
import com.daohuyen.dell.storeadmin.service.retrofit.ApiClient;
import com.daohuyen.dell.storeadmin.service.retrofit.InteratoryService.InteratoryService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InventoryInteratorIpl implements InventoryInterator {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public InventoryInteratorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }



    @Override
    public void onViewDestroy() {
        compositeDisposable.clear();
    }

    @Override
    public void getProduct(String date, int pageIndex, int pageSize, OnGetProducSuccessListener listener) {
        Disposable disposable = ApiClient.getClient().create(InteratoryService.class)
                .getAllProductInteratory(date,pageIndex, pageSize,null, null)
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



