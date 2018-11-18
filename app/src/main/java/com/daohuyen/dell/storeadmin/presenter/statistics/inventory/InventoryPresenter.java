package com.daohuyen.dell.storeadmin.presenter.statistics.inventory;

import com.daohuyen.dell.storeadmin.presenter.BasePresenter;

public interface InventoryPresenter extends BasePresenter {
    void loadMoreProductPreviews(String date);
    void refreshProductPreviews(String date);

}
