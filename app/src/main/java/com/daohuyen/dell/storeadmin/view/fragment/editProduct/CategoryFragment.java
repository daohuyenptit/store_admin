package com.daohuyen.dell.storeadmin.view.fragment.editProduct;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.adapter.CateAdaper;
import com.daohuyen.dell.storeadmin.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.storeadmin.custom.LoadingDialog;
import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;
import com.daohuyen.dell.storeadmin.presenter.product.category.CatePresenter;
import com.daohuyen.dell.storeadmin.presenter.product.category.CatePresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements RecyclerViewAdapter.OnItemClickListener,CategoryFragmentView{
    @BindView(R.id.recycler_category_one)
    RecyclerView recycler_category_one;
    @BindView(R.id.recycler_category_two)
    RecyclerView recycler_category_two;
    @BindView(R.id.recycler_category_three)
    RecyclerView recycler_category_three;

    CateAdaper adapter1;
    CateAdaper adapter2;
    CateAdaper adapter3;
    List<CategoryViewModel> categories;
    CatePresenter presenter;
    LoadingDialog loadingDialog;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_category2, container, false);
        ButterKnife.bind(this,view);
        adapter1=new CateAdaper(getContext(),false);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_category_one.setLayoutManager(manager1);
        recycler_category_one.setAdapter(adapter1);
        adapter2=new CateAdaper(getContext(),false);
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_category_two.setLayoutManager(manager2);
        recycler_category_two.setAdapter(adapter2);
        adapter3=new CateAdaper(getContext(),false);
        LinearLayoutManager manager3 = new LinearLayoutManager(getActivity());
        manager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_category_three.setLayoutManager(manager3);
        recycler_category_three.setAdapter(adapter3);

        return  view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }
    public void initData(){
        loadingDialog=new LoadingDialog(getContext());
        presenter = new CatePresenterImpl(getActivity(),this);
        presenter.loadAllCategory();
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {



    }

    @Override
    public void showLoading() {
        loadingDialog.show();

    }

    @Override
    public void hideLoading() {
        loadingDialog.hide();

    }

    @Override
    public void loadAllCategories(List<CategoryViewModel> list) {
        categories= list;
        ArrayList<CategoryViewModel> list1=new ArrayList<>();
        ArrayList<CategoryViewModel> list2= new ArrayList<>();
        ArrayList<CategoryViewModel> list3= new ArrayList<>();
        for (CategoryViewModel categoryViewModel: categories) {
            if(categoryViewModel.getIdPL().equals("1")){
                list1.add(categoryViewModel);
            }else if(categoryViewModel.getIdPL().equals("2")){
                list2.add(categoryViewModel);
            }else if(categoryViewModel.getIdPL().equals("3")){
                list3.add(categoryViewModel);
            }
        }
        adapter1.addModels(list1,false);
        adapter2.addModels(list2,false);
        adapter3.addModels(list3,false);

    }

}
