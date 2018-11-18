package com.daohuyen.dell.storeadmin.presenter.login;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.daohuyen.dell.storeadmin.common.Constants;
import com.daohuyen.dell.storeadmin.common.Utils;
import com.daohuyen.dell.storeadmin.model.body.UserBody;
import com.daohuyen.dell.storeadmin.model.view.CustomerView;
import com.daohuyen.dell.storeadmin.view.login.LoginActivityView;
public class LoginPresenterIpl implements LoginPresenter {
    private Context context;
    private LoginActivityView loginActivityView;
    private LoginInterator loginInterator;

    public LoginPresenterIpl(Context context, LoginActivityView loginActivityView) {
        this.context = context;
        this.loginActivityView = loginActivityView;
        this.loginInterator = new LoginInteratorIpl(context);
    }

    @Override
    public void  fetchLogin(UserBody userBody) {
        loginActivityView.showProgress();
        loginInterator.getLogin(userBody, new OnGetLoginSuccessListener() {
            @Override
            public void onSuccess() {

                Utils.setSharePreferenceValues(context, Constants.STATUS_LOGIN, Constants.LOGIN_TRUE);
//                Utils.setSharePreferenceValues(context, Constants.CUSTOMER_ID, customer.getId());
//                Utils.setSharePreferenceValues(context, Constants.CUSTOMER_NAME, customer.getFullName());
//                Utils.s(context,customerView);
                loginActivityView.backToHomeScreen(Activity.RESULT_OK);
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            }
        });
        loginActivityView.hideProgress();

    }

    @Override
    public void onViewDestroy() {
        loginInterator.onViewDestroy();

    }
}
