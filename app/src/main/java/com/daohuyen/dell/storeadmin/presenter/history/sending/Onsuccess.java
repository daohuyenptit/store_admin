package com.daohuyen.dell.storeadmin.presenter.history.sending;

import com.daohuyen.dell.storeadmin.model.view.BillView;

import java.util.List;

public interface Onsuccess {
    void OnsuccessComplete(List<BillView> billViews);
    void OnError(String msg);
}
