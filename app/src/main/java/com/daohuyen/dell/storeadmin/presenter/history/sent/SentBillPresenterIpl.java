package com.daohuyen.dell.storeadmin.presenter.history.sent;

import android.content.Context;
import android.widget.Toast;

import com.daohuyen.dell.storeadmin.model.view.BillView;
import com.daohuyen.dell.storeadmin.view.fragment.historyorder.PermitView;
import com.daohuyen.dell.storeadmin.view.fragment.historyorder.sent.SentView;

import java.util.List;

public class SentBillPresenterIpl implements SentPresenter {
    private Context context;
    private SentView permitView;
    private SentInteractor sentInteractor;

    public SentBillPresenterIpl(Context context, SentView permitView) {
        this.context = context;
        this.permitView = permitView;
        this.sentInteractor =new SentInteractorIpl(context);
    }

    @Override
    public void loadAllBills() {
        permitView.showLoading();
        sentInteractor.getAllBills(new Onsuccess() {
            @Override
            public void OnsuccessComplete(List<BillView> billViews) {
                permitView.loadAllHistoryBills(billViews);
                permitView.hideLoading();
            }

            @Override
            public void OnError(String msg) {
                Toast.makeText(context, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                permitView.hideLoading();

            }
        });

    }

    @Override
    public void onViewDestroy() {
        sentInteractor.onViewDestroy();

    }
}
