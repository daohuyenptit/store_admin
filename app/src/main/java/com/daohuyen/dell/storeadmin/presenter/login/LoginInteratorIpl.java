package com.daohuyen.dell.storeadmin.presenter.login;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import com.daohuyen.dell.storeadmin.common.ResponseCode;
import com.daohuyen.dell.storeadmin.model.body.UserBody;
import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.model.view.CustomerView;
import com.daohuyen.dell.storeadmin.service.retrofit.ApiClient;
import com.daohuyen.dell.storeadmin.service.retrofit.admin.LoginService;

public class LoginInteratorIpl implements LoginInterator {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public LoginInteratorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }


    @Override
    public void onViewDestroy() {
    compositeDisposable.clear();
    }

    @Override
    public void getLogin(UserBody userBody, OnGetLoginSuccessListener listener) {
        Observable<Response<ResponseBody<String>>> observable =
                ApiClient.getClient().create(LoginService.class).getAdminLogin(userBody);
        Disposable disposable = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response->{
                            switch (response.code()){
                                case ResponseCode.OK:{
                                    listener.onSuccess();
                                    break;
                                }
                                default:{
                                    listener.onError(response.message());
                                    break;
                                }
                            }
                        },
                        error->{
                            listener.onError(error.getMessage());
                        }
                );
        compositeDisposable.add(disposable);
    }
}
