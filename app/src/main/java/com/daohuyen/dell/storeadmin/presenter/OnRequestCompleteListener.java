package com.daohuyen.dell.storeadmin.presenter;



public interface OnRequestCompleteListener {
    void onSuccess();
    void onServerError(String message);
}
