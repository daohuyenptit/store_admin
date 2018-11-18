package com.daohuyen.dell.storeadmin.view.fragment.statistics.allbestseller;

import com.daohuyen.dell.storeadmin.model.view.ItemView;

public interface AllView {
    void showLoading();
    void hideLoading();
    void loadAllBest(ItemView itemView);
}
