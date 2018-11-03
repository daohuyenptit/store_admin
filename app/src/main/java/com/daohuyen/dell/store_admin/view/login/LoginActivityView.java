package com.daohuyen.dell.store_admin.view.login;
public interface LoginActivityView {
    void showProgress();
    void hideProgress();
    void showEmailInputError(String message);
    void showPasswordInputError(String message);
    void backToHomeScreen(int resultCode);
}
