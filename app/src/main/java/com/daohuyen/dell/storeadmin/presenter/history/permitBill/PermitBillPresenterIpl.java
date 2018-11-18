package com.daohuyen.dell.storeadmin.presenter.history.permitBill;

import android.content.Context;
import android.widget.Toast;

import com.daohuyen.dell.storeadmin.model.view.BillView;
import com.daohuyen.dell.storeadmin.view.fragment.historyorder.PermitView;

import java.util.List;
import java.util.Set;

public class PermitBillPresenterIpl implements PermitPresenter {
    private Context context;
    private PermitView permitView;
    private PermitInteractor permitInteractor;

    public PermitBillPresenterIpl(Context context, PermitView permitView) {
        this.context = context;
        this.permitView = permitView;
        this.permitInteractor =new PermitInteractorIpl(context);
    }

    @Override
    public void loadAllBills() {
        permitView.showLoading();
        permitInteractor.getAllBills(new Onsuccess() {
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
    public void editPermit(Set<String> listID) {
        permitView.showLoading();
        permitInteractor.editBill(listID, new OnGetEditSuccess() {
            @Override
            public void onSuccess(String msg) {
                permitView.loadAllBills();
                permitView.hideLoading();
                Toast.makeText(context, "Luu thanh cong"+msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String message) {
                Toast.makeText(context, "Chưa chọn đơn hàng", Toast.LENGTH_SHORT).show();
                permitView.hideLoading();
            }
        });


    }

    @Override
    public void deleteBill(String id) {
        permitView.showLoading();
        permitInteractor.deleteBill(id, new OnDeleteBill() {
            @Override
            public void onSuccess(String msg) {
                permitView.loadAllBills();
                permitView.hideLoading();
                Toast.makeText(context, "Xóa thành công"+msg, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(String message) {
                Toast.makeText(context, "khong thanh cong", Toast.LENGTH_SHORT).show();
                permitView.hideLoading();

            }
        });
    }

    @Override
    public void onViewDestroy() {
        permitInteractor.onViewDestroy();

    }
}
