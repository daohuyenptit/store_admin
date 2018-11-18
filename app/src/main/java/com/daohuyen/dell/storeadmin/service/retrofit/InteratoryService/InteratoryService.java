package com.daohuyen.dell.storeadmin.service.retrofit.InteratoryService;

import com.daohuyen.dell.storeadmin.common.RequestConstants;
import com.daohuyen.dell.storeadmin.model.PageList;
import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InteratoryService {
    @GET("/api/products/products/inventory/{date}")
    Observable<Response<ResponseBody<PageList<ProductViewModel>>>> getAllProductInteratory(@Path("date") String date,
                                                                                             @Query(RequestConstants.PAGE_INDEX_QUERY) int pageIndex,
                                                                                             @Query(RequestConstants.PAGE_SIZE_QUERY) int pageSize,
                                                                                             @Query(RequestConstants.SORT_BY_QUERY) String sortBy,
                                                                                             @Query(RequestConstants.SORT_TYPE_QUERY) String sortType);
}
