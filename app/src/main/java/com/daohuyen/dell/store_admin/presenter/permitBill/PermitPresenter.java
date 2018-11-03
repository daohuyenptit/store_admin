package com.daohuyen.dell.store_admin.presenter.permitBill;

import com.daohuyen.dell.store_admin.presenter.BasePresenter;

import java.util.Set;

public interface PermitPresenter extends BasePresenter {
    void loadAllBills();
    void editPermit(Set<String> listID);
}
