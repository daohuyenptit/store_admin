package com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller;

import com.daohuyen.dell.storeadmin.model.view.ItemView;

public interface OnsuccessStatistics {
    void OnsuccessComplete(ItemView itemView);
    void OnError(String msg);
}
