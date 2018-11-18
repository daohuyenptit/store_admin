package com.daohuyen.dell.storeadmin.view.fragment.statistics.monthbestseller;

import com.daohuyen.dell.storeadmin.model.view.ItemView;

public interface MonthView {
    void showLoading();
    void hideLoading();
    void loadAllBest(ItemView itemView);
}
