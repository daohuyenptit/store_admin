package com.daohuyen.dell.storeadmin.service.retrofit.admin;
import com.daohuyen.dell.storeadmin.model.Customer;
import com.daohuyen.dell.storeadmin.model.body.UserBody;
import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.model.view.CustomerView;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/api/employee/login")
    Observable<Response<ResponseBody<String>>> getAdminLogin(@Body UserBody userBody);
}
