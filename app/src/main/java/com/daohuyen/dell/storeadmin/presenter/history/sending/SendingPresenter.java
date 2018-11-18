package com.daohuyen.dell.storeadmin.presenter.history.sending;

import com.daohuyen.dell.storeadmin.presenter.BasePresenter;

import java.util.Set;

public interface SendingPresenter extends BasePresenter {
    void loadAllBills();
    void editPermit(Set<String> listID);
    void deleteBill(String id);
}
