package com.daohuyen.dell.store_admin.service.retrofit.permitbill;

import com.daohuyen.dell.store_admin.model.response.ResponseBody;
import com.daohuyen.dell.store_admin.model.view.BillView;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PermitService {
    @GET("/api/admin/getAllBillnotPermit")
    Observable<Response<ResponseBody<List<BillView>>>> getAllBills();
    @PUT("/api/admin/editbill")
    Observable<Response<ResponseBody<String>>> editPermit(@Body Set<String> listID);
}
