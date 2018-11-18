package com.daohuyen.dell.storeadmin.presenter.login;

import com.daohuyen.dell.storeadmin.model.body.UserBody;
import com.daohuyen.dell.storeadmin.presenter.BasePresenter;

public interface LoginPresenter extends BasePresenter {
    void fetchLogin(UserBody userBody);


}
