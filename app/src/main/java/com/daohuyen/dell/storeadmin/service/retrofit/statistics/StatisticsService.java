package com.daohuyen.dell.storeadmin.service.retrofit.statistics;

import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.model.view.ItemView;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatisticsService {
    @GET("/api/products/getAllBestSelllerProduct")
    Observable<Response<ResponseBody<ItemView>>> getBestSellerAll();
    @GET("/api/products/getMonthBestSelllerProduct/{sd}")
    Observable<Response<ResponseBody<ItemView>>> getMonthBestSellerAll(@Path("sd") String groupID);
    @GET("/api/products/getDayBestSelllerProduct/{sd}")
    Observable<Response<ResponseBody<ItemView>>> getDayBestSellerAll(@Path("sd") String groupID);
}
