package com.daohuyen.dell.storeadmin.presenter.statistics.daybestseller;

import android.content.Context;
import android.widget.Toast;

import com.daohuyen.dell.storeadmin.model.view.ItemView;
import com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller.OnsuccessStatistics;
import com.daohuyen.dell.storeadmin.view.fragment.statistics.daybestSeller.DayView;
import com.daohuyen.dell.storeadmin.view.fragment.statistics.monthbestseller.MonthView;

public class DayPresenterIpl implements DayPresenter {
    private Context context;
    private DayView permitView;
    private DayInteractor permitInteractor;

    public DayPresenterIpl(Context context, DayView permitView) {
        this.context = context;
        this.permitView = permitView;
        this.permitInteractor =new DayInteractorIpl(context);
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
