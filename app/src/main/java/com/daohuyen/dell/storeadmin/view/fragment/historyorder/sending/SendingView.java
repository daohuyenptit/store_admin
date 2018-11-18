package com.daohuyen.dell.storeadmin.view.fragment.historyorder.sending;

import com.daohuyen.dell.storeadmin.model.view.BillView;

import java.util.List;

public interface SendingView {
    void showLoading();
    void hideLoading();
    void deleteAllSelect();
    void loadAllHistoryBills(List<BillView> list);
    void loadAllBills();
}
