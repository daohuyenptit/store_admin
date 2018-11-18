package com.daohuyen.dell.storeadmin.view.fragment.statistics.daybestSeller;

import com.daohuyen.dell.storeadmin.model.view.ItemView;

public interface DayView {
    void showLoading();
    void hideLoading();
    void loadAllBest(ItemView itemView);
}
