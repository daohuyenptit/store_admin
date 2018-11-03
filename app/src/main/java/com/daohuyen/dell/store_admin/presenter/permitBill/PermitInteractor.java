package com.daohuyen.dell.store_admin.presenter.permitBill;


import com.daohuyen.dell.store_admin.presenter.BaseInteractor;

import java.util.Set;

public interface PermitInteractor extends BaseInteractor {
    void getAllBills(Onsuccess onsuccess);
    void editBill(Set<String> listID, OnGetEditSuccess listener);
}
