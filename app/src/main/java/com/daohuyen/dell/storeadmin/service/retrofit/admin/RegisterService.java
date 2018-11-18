package com.daohuyen.dell.storeadmin.service.retrofit.admin;
import com.daohuyen.dell.storeadmin.common.RequestConstants;
import com.daohuyen.dell.storeadmin.model.body.UserBody;
import com.daohuyen.dell.storeadmin.model.response.ResponseBody;

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
