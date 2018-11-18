package com.daohuyen.dell.storeadmin.view.fragment.historyorder;
import com.daohuyen.dell.storeadmin.model.view.BillView;

import java.util.List;

public interface PermitView {
    void showLoading();
    void hideLoading();
    void deleteAllSelect();
    void loadAllHistoryBills(List<BillView> list);
    void loadAllBills();

}
