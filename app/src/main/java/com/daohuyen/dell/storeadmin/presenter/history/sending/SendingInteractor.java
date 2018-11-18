package com.daohuyen.dell.storeadmin.presenter.history.sending;


import com.daohuyen.dell.storeadmin.presenter.BaseInteractor;

import java.util.Set;

public interface SendingInteractor extends BaseInteractor {
    void getAllBills(Onsuccess onsuccess);
    void editBill(Set<String> listID, OnGetEditSuccess listener);
    void deleteBill(String id, OnDeleteBill listener);
}
