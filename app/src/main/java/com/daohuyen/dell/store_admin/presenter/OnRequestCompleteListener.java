package com.daohuyen.dell.store_admin.presenter;



public interface OnRequestCompleteListener {
    void onSuccess();
    void onServerError(String message);
}
