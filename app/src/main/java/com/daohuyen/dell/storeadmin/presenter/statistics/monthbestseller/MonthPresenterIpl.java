package com.daohuyen.dell.storeadmin.presenter.statistics.monthbestseller;

import android.content.Context;
import android.widget.Toast;

import com.daohuyen.dell.storeadmin.model.view.ItemView;
import com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller.OnsuccessStatistics;
import com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller.StatisticsInteractor;
import com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller.StatisticsInteractorIpl;
import com.daohuyen.dell.storeadmin.view.fragment.statistics.allbestseller.AllView;
import com.daohuyen.dell.storeadmin.view.fragment.statistics.monthbestseller.MonthView;

public class MonthPresenterIpl implements MonthPresenter {
    private Context context;
    private MonthView permitView;
    private MonthInteractor permitInteractor;

    public MonthPresenterIpl(Context context, MonthView permitView) {
        this.context = context;
        this.permitView = permitView;
        this.permitInteractor =new MonthInteractorIpl(context);
    }

    @Override
    public void onViewDestroy() {
        permitInteractor.onViewDestroy();

    }

    @Override
    public void getAllBestSeller(String sd) {

        permitView.showLoading();
        permitInteractor.getAllBest(sd,new OnsuccessStatistics() {
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
