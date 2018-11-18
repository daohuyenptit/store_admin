package com.daohuyen.dell.storeadmin.presenter.product.editProduct;

import android.content.Context;

import com.daohuyen.dell.storeadmin.common.ResponseCode;
import com.daohuyen.dell.storeadmin.model.body.ProductBody;
import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
import com.daohuyen.dell.storeadmin.service.retrofit.ApiClient;
import com.daohuyen.dell.storeadmin.service.retrofit.product.ProductService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class EditProductInteractorIpl implements EditProductInteractor {
    Context context;
    CompositeDisposable compositeDisposable;

    public EditProductInteractorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void editProduct(String productID, ProductBody productViewModel, OnGetEditSuccess listener) {
        Observable<Response<ResponseBody<ProductViewModel>>> observable = ApiClient.getClient().create(ProductService.class)
                .editProduct(productID,productViewModel);
        Disposable disposable = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        response -> {
                            switch (response.code()) {
                                case ResponseCode.OK: {
                                    listener.onSuccess(response.body().getData());
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
