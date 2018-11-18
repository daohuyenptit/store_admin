package com.daohuyen.dell.storeadmin.service.retrofit.product;


import com.daohuyen.dell.storeadmin.common.RequestConstants;
import com.daohuyen.dell.storeadmin.model.PageList;
import com.daohuyen.dell.storeadmin.model.body.ProductBody;
import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {
    @PUT("api/products/editproduct/{id}")
    Observable<Response<ResponseBody<ProductViewModel>>> editProduct(@Path("id") String productID, @Body ProductBody body);
    @GET("/api/products/productdetail/{id}")
    Observable<Response<ResponseBody<ProductViewModel>>> getProductDetail(@Path("id") String productID);
    @POST("api/products/addproduct/{id}")
    Observable<Response<ResponseBody<String>>> addProduct(@Path("id") String categoryID, @Body ProductBody productViewModel);
    @GET("/api/products/getProducts/{id}")
    Observable<Response<ResponseBody<PageList<ProductViewModel>>>> getAllProductByCategoryID(@Path("id") String categoryID,
                                                                                             @Query(RequestConstants.PAGE_INDEX_QUERY) int pageIndex,
                                                                                             @Query(RequestConstants.PAGE_SIZE_QUERY) int pageSize,
                                                                                             @Query(RequestConstants.SORT_BY_QUERY) String sortBy,
                                                                                             @Query(RequestConstants.SORT_TYPE_QUERY) String sortType);
}
