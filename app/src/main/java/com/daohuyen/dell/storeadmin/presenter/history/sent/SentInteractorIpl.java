package com.daohuyen.dell.storeadmin.presenter.history.sent;

import android.content.Context;

import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.model.view.BillView;
import com.daohuyen.dell.storeadmin.service.retrofit.ApiClient;
import com.daohuyen.dell.storeadmin.service.retrofit.permitbill.PermitService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SentInteractorIpl implements SentInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public SentInteractorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void getAllBills(Onsuccess listener) {
        Observable<Response<ResponseBody<List<BillView>>>> observable = ApiClient.getClient()
                .create(PermitService.class).getAllBillsSent();

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
    public void onViewDestroy() {
        compositeDisposable.clear();

    }
}
