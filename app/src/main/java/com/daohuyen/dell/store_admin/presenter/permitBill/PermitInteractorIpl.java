package com.daohuyen.dell.store_admin.presenter.permitBill;
import android.content.Context;

import com.daohuyen.dell.store_admin.common.ResponseCode;
import com.daohuyen.dell.store_admin.model.response.ResponseBody;
import com.daohuyen.dell.store_admin.model.view.BillView;
import com.daohuyen.dell.store_admin.service.retrofit.ApiClient;
import com.daohuyen.dell.store_admin.service.retrofit.permitbill.PermitService;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class PermitInteractorIpl implements PermitInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public PermitInteractorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void getAllBills(Onsuccess listener) {
        Observable<Response<ResponseBody<List<BillView>>>> observable = ApiClient.getClient()
                .create(PermitService.class).getAllBills();

        Disposable disposable = observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                    listener.OnsuccessComplete(response.body().getData());
                }, error -> {
                    listener.OnError("Co loi roi");
                });
        compositeDisposable.add(disposable);

    }
    @Override
    public void editBill(Set<String> listID, OnGetEditSuccess listener) {

        Observable<Response<ResponseBody<String>>> observable = ApiClient.getClient().create(PermitService.class)
                .editPermit(listID);
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
