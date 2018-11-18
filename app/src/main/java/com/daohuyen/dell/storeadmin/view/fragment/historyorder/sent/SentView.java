package com.daohuyen.dell.storeadmin.view.fragment.historyorder.sent;

import com.daohuyen.dell.storeadmin.model.view.BillView;

import java.util.List;

public interface SentView {
    void showLoading();
    void hideLoading();
    void loadAllHistoryBills(List<BillView> list);
}
