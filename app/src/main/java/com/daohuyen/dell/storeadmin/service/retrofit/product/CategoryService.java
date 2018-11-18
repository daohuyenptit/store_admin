package com.daohuyen.dell.storeadmin.service.retrofit.product;

import com.daohuyen.dell.storeadmin.common.RequestConstants;
import com.daohuyen.dell.storeadmin.model.PageList;
import com.daohuyen.dell.storeadmin.model.response.ResponseBody;
import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CategoryService {
    @GET("api/products/category")
    Observable<Response<ResponseBody<List<CategoryViewModel>>>> getAllCategories();
    @GET("api/products/category/{id}")
    Observable<Response<ResponseBody<List<CategoryViewModel>>>> getAllCategory(@Path("id") String groupID);
    @GET("api/products/newest")
    Observable<Response<ResponseBody<PageList<ProductViewModel>>>> getNewProduct(@Query(RequestConstants.PAGE_INDEX_QUERY) int pageIndex,
                                                                                 @Query(RequestConstants.PAGE_SIZE_QUERY) int pageSize,
                                                                                 @Query(RequestConstants.SORT_BY_QUERY) String sortBy,
                                                                                 @Query(RequestConstants.SORT_TYPE_QUERY) String sortType);
}
