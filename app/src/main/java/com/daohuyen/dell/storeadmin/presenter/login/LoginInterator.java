package com.daohuyen.dell.storeadmin.presenter.login;


import com.daohuyen.dell.storeadmin.model.body.UserBody;
import com.daohuyen.dell.storeadmin.presenter.BaseInteractor;

public interface LoginInterator extends BaseInteractor {
    void getLogin(UserBody userBody, OnGetLoginSuccessListener onGetLoginSuccessListener);
}
