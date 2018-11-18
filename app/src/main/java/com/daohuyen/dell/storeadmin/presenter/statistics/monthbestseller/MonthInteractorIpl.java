package com.daohuyen.dell.storeadmin.presenter.statistics.monthbestseller;

import android.content.Context;

import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.model.view.ItemView;
import com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller.OnsuccessStatistics;
import com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller.StatisticsInteractor;
import com.daohuyen.dell.storeadmin.service.retrofit.ApiClient;
import com.daohuyen.dell.storeadmin.service.retrofit.statistics.StatisticsService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MonthInteractorIpl implements MonthInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public MonthInteractorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }


    @Override
    public void onViewDestroy() {
        compositeDisposable.clear();

    }

    @Override
    public void getAllBest(String sd,OnsuccessStatistics listener) {
        Observable<Response<ResponseBody<ItemView>>> observable = ApiClient.getClient()
                .create(StatisticsService.class).getMonthBestSellerAll(sd);

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
}
