package com.daohuyen.dell.storeadmin.presenter.history.sending;

import android.content.Context;

import com.daohuyen.dell.storeadmin.common.ResponseCode;
import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.model.view.BillView;
import com.daohuyen.dell.storeadmin.service.retrofit.ApiClient;
import com.daohuyen.dell.storeadmin.service.retrofit.permitbill.PermitService;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SendingInteractorIpl implements SendingInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public SendingInteractorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void getAllBills(Onsuccess listener) {
        Observable<Response<ResponseBody<List<BillView>>>> observable = ApiClient.getClient()
                .create(PermitService.class).getAllBillsSending();

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
                .editPermitSending(listID);
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
    public void deleteBill(String id, OnDeleteBill listener) {
        Observable<Response<ResponseBody<String>>> observable = ApiClient.getClient().create(PermitService.class)
                .deleteBill(id);
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
