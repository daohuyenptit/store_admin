package com.daohuyen.dell.storeadmin.presenter.product.category;

import android.content.Context;
import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;
import com.daohuyen.dell.storeadmin.service.retrofit.ApiClient;
import com.daohuyen.dell.storeadmin.service.retrofit.product.CategoryService;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CateInteratorIpl implements CateInterator {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public CateInteratorIpl(Context context) {
        this.context = context;
        this.compositeDisposable=new CompositeDisposable();
    }



    @Override
    public void getCategory(OnGetCategorySuccessListener listener) {
        Observable<Response<ResponseBody<List<CategoryViewModel>>>> observable = ApiClient.getClient()
                .create(CategoryService.class).getAllCategories();

        Disposable disposable = observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                    listener.onSuccess(response.body().getData());
                }, error -> {
                    listener.onError("Co loi xay ra");
                });
        compositeDisposable.add(disposable);

    }


    @Override
    public void onViewDestroy() {
        compositeDisposable.clear();
    }
}
