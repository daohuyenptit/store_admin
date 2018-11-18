package com.daohuyen.dell.storeadmin.presenter.statistics.monthbestseller;

import com.daohuyen.dell.storeadmin.model.view.ItemView;

public interface OnsuccessMonth {
    void OnsuccessComplete(ItemView itemView);
    void OnError(String msg);
}
