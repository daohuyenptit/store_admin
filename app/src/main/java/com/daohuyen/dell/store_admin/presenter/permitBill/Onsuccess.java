package com.daohuyen.dell.store_admin.presenter.permitBill;

import com.daohuyen.dell.store_admin.model.view.BillView;

import java.util.List;

public interface Onsuccess {
    void OnsuccessComplete(List<BillView> billViews);
    void OnError(String msg);
}
