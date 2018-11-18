package com.daohuyen.dell.storeadmin.presenter.product.category;

import android.content.Context;
import android.widget.Toast;
import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;
import com.daohuyen.dell.storeadmin.view.fragment.editProduct.CategoryFragmentView;
import java.util.List;


public class CatePresenterImpl implements CatePresenter {
    private Context context;
    private CategoryFragmentView homeFragmentView;
    private CateInterator cateInterator;
    int currentpage=0;

    public CatePresenterImpl(Context context, CategoryFragmentView homeFragmentView) {
        this.context = context;
        this.homeFragmentView = homeFragmentView;
        this.cateInterator = new CateInteratorIpl(context);
    }

    @Override
    public void onViewDestroy() {
        cateInterator.onViewDestroy();
    }

    @Override
    public void loadAllCategory() {
        homeFragmentView.showLoading();
        cateInterator.getCategory(new OnGetCategorySuccessListener() {
           @Override
           public void onSuccess(List<CategoryViewModel> categories) {
               homeFragmentView.loadAllCategories(categories);
               homeFragmentView.hideLoading();

           }

           @Override
           public void onError(String msg) {
               Toast.makeText(context, "Co loi xay ra", Toast.LENGTH_SHORT).show();
               homeFragmentView.hideLoading();

           }
       });
    }


}
