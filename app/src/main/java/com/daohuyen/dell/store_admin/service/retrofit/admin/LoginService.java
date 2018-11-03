package com.daohuyen.dell.store_admin.service.retrofit.admin;
import com.daohuyen.dell.store_admin.model.body.UserBody;
import com.daohuyen.dell.store_admin.model.response.ResponseBody;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/api/admin/login")
    Observable<Response<ResponseBody<String>>> getAdminLogin(@Body UserBody userBody);
}
