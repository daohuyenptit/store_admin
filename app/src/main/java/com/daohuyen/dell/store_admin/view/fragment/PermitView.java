package com.daohuyen.dell.store_admin.view.fragment;
import com.daohuyen.dell.store_admin.model.view.BillView;

import java.util.List;

public interface PermitView {
    void showLoading();
    void hideLoading();
    void loadAllHistoryBills(List<BillView> list);
    void loadAllBills();

}
