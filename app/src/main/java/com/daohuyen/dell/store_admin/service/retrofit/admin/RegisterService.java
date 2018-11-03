package com.daohuyen.dell.store_admin.service.retrofit.admin;
import com.daohuyen.dell.store_admin.common.RequestConstants;
import com.daohuyen.dell.store_admin.model.body.UserBody;
import com.daohuyen.dell.store_admin.model.response.ResponseBody;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RegisterService {
    @POST("/api/admin/register")
    Observable<Response<ResponseBody<String>>> CustomerRegister(@Header(RequestConstants.AUTHORIZATION) String base64Account,
                                                                @Body UserBody body);
}
