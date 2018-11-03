package com.daohuyen.dell.store_admin.presenter.login;


import com.daohuyen.dell.store_admin.model.body.UserBody;
import com.daohuyen.dell.store_admin.presenter.BaseInteractor;

public interface LoginInterator extends BaseInteractor {
    void getLogin(UserBody userBody, OnGetLoginSuccessListener onGetLoginSuccessListener);
}
