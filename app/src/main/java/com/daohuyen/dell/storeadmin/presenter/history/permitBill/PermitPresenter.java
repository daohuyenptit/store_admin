package com.daohuyen.dell.storeadmin.presenter.history.permitBill;

import com.daohuyen.dell.storeadmin.presenter.BasePresenter;

import java.util.Set;

public interface PermitPresenter extends BasePresenter {
    void loadAllBills();
    void editPermit(Set<String> listID);
    void deleteBill(String id);
}
