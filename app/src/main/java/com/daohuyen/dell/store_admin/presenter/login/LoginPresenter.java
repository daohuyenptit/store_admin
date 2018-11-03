package com.daohuyen.dell.store_admin.presenter.login;

import com.daohuyen.dell.store_admin.model.body.UserBody;
import com.daohuyen.dell.store_admin.presenter.BasePresenter;

public interface LoginPresenter extends BasePresenter {
    void fetchLogin(UserBody userBody);


}
