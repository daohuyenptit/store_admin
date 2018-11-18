package com.daohuyen.dell.storeadmin.presenter.login;

import com.daohuyen.dell.storeadmin.model.view.CustomerView;

public interface OnGetLoginSuccessListener {
    void onSuccess();
    void onError(String msg);
}
