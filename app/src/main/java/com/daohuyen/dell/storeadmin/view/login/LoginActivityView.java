package com.daohuyen.dell.storeadmin.view.login;
public interface LoginActivityView {
    void showProgress();
    void hideProgress();
    void showEmailInputError(String message);
    void showPasswordInputError(String message);
    void backToHomeScreen(int resultCode);
}
