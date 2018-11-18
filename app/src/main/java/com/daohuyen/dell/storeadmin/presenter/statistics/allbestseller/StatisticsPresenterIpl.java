package com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller;

import android.content.Context;
import android.widget.Toast;

import com.daohuyen.dell.storeadmin.model.view.ItemView;
import com.daohuyen.dell.storeadmin.view.fragment.statistics.allbestseller.AllView;

public class StatisticsPresenterIpl implements StatisticsPresenter {
    private Context context;
    private AllView permitView;
    private StatisticsInteractor permitInteractor;

    public StatisticsPresenterIpl(Context context, AllView permitView) {
        this.context = context;
        this.permitView = permitView;
        this.permitInteractor =new StatisticsInteractorIpl(context);
    }

    @Override
    public void onViewDestroy() {
        permitInteractor.onViewDestroy();

    }

    @Override
    public void getAllBestSeller() {
        permitView.showLoading();
        permitInteractor.getAllBest(new OnsuccessStatistics() {
            @Override
            public void OnsuccessComplete(ItemView itemView) {
                permitView.loadAllBest(itemView);
                permitView.hideLoading();
            }

            @Override
            public void OnError(String msg) {
                Toast.makeText(context, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                permitView.hideLoading();

            }
        });


    }
}
