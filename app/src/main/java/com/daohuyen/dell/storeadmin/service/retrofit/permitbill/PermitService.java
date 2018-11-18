package com.daohuyen.dell.storeadmin.service.retrofit.permitbill;

import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.model.view.BillView;
import java.util.List;
import java.util.Set;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
public interface PermitService {
    @GET("/api/products/getAllBillnotPermit")
    Observable<Response<ResponseBody<List<BillView>>>> getAllBills();
    @GET("/api/products/getAllBillAdminSending")
    Observable<Response<ResponseBody<List<BillView>>>> getAllBillsSending();
    @GET("/api/products/getAllBillAdminSent")
    Observable<Response<ResponseBody<List<BillView>>>> getAllBillsSent();
    @PUT("/api/products/editbill")
    Observable<Response<ResponseBody<String>>> editPermit(@Body Set<String> listID);
    @PUT("/api/products/editbillSending")
    Observable<Response<ResponseBody<String>>> editPermitSending(@Body Set<String> listID);
    @PUT("/api/products/cancelbill")
    Observable<Response<ResponseBody<String>>> deleteBill(@Body String id);

}
