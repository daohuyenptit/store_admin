package com.daohuyen.dell.storeadmin.presenter.history.sent;


import com.daohuyen.dell.storeadmin.presenter.BaseInteractor;

import java.util.Set;

public interface SentInteractor extends BaseInteractor {
    void getAllBills(Onsuccess onsuccess);
}
