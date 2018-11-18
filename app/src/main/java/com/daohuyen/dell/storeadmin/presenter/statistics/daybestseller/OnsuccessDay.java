package com.daohuyen.dell.storeadmin.presenter.statistics.daybestseller;

import com.daohuyen.dell.storeadmin.model.view.ItemView;

public interface OnsuccessDay {
    void OnsuccessComplete(ItemView itemView);
    void OnError(String msg);
}
