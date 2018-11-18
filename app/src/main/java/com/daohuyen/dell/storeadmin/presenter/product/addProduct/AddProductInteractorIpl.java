package com.daohuyen.dell.storeadmin.presenter.product.addProduct;

import android.content.Context;

import com.daohuyen.dell.storeadmin.common.ResponseCode;
import com.daohuyen.dell.storeadmin.model.body.ProductBody;
import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.presenter.history.permitBill.OnGetEditSuccess;
import com.daohuyen.dell.storeadmin.service.retrofit.ApiClient;
import com.daohuyen.dell.storeadmin.service.retrofit.product.ProductService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AddProductInteractorIpl implements AddProductInteractor {
    Context context;
    CompositeDisposable compositeDisposable;

    public AddProductInteractorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void addProduct(String categoryID, ProductBody productViewModel, OnGetAddSuccess listener) {
        Observable<Response<ResponseBody<String>>> observable = ApiClient.getClient().create(ProductService.class)
                .addProduct(categoryID,productViewModel);
        Disposable disposable = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        response -> {
                            switch (response.code()) {
                                case ResponseCode.OK: {
                                    listener.onSuccess();
                                    break;
                                }
                                case ResponseCode.NOT_FOUND: {
                                    listener.onError(response.message());
                                    break;
                                }
                                default:{
                                    listener.onError(response.message()+"deafault");
                                    break;
                                }
                            }
                        },
                        error -> {
                            listener.onError(error.getMessage());
                        }
                );
        compositeDisposable.add(disposable);

    }

    @Override
    public void onViewDestroy() {
        compositeDisposable.clear();

    }

}
