package com.daohuyen.dell.storeadmin.presenter.product.list_category;

import android.content.Context;
import android.widget.Toast;

import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;
import com.daohuyen.dell.storeadmin.view.product.addProduct.CategoryView;
import com.daohuyen.dell.storeadmin.view.product.addProduct.ProductGroupView;
import java.util.List;
public class CategoryPresenterImpl implements CategoryPresenter {
    private Context context;
    private CategoryView categoryView;
    private CategoryInterator categoryInterator;

    public CategoryPresenterImpl(Context context,CategoryView categoryView) {
        this.context = context;
        this.categoryView = categoryView;
        this.categoryInterator = new CategoryInteratorIpl(context);
    }

    @Override
    public void onViewDestroy() {
        categoryInterator.onViewDestroy();
    }

    @Override
    public void loadAllCategory(String productGroupID) {
        categoryView.showProgress();
        categoryInterator.getCategory(productGroupID,new OnGetCategorySuccessListener() {
           @Override
           public void onSuccess(List<CategoryViewModel> categories) {
               categoryView.loadAllCategories(categories);
               categoryView.hideProgress();

           }

           @Override
           public void onError(String msg) {
               Toast.makeText(context, "Co loi xay ra", Toast.LENGTH_SHORT).show();
               categoryView.hideProgress();

           }
       });
    }


}
