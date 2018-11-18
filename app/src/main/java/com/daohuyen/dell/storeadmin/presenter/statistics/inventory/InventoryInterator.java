package com.daohuyen.dell.storeadmin.presenter.statistics.inventory;

import com.daohuyen.dell.storeadmin.presenter.BaseInteractor;

public interface InventoryInterator extends BaseInteractor {
    void getProduct(String date, int pageIndex, int pageSize, OnGetProducSuccessListener onGetProducSuccessListener);
}
