package com.daohuyen.dell.storeadmin.view.fragment.statistics.allbestseller;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.adapter.AllBestSellerAdaper;
import com.daohuyen.dell.storeadmin.common.Utils;
import com.daohuyen.dell.storeadmin.custom.LoadingDialog;
import com.daohuyen.dell.storeadmin.model.response.ItemBody;
import com.daohuyen.dell.storeadmin.model.view.ItemView;
import com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller.StatisticsPresenter;
import com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller.StatisticsPresenterIpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment implements AllView{
    LoadingDialog loadingDialog;
    AllBestSellerAdaper allBestSellerAdaper;
    StatisticsPresenter presenter;
    @BindView(R.id.rc_all)
    RecyclerView rc_all;
    @BindView(R.id.txt_products)
    TextView txt_products;
    @BindView(R.id.txt_order)
    TextView txt_order;
    @BindView(R.id.txt_total)
    TextView txt_total;



    public AllFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_all, container, false);
        loadingDialog=new LoadingDialog(getContext());
        ButterKnife.bind(this,view);
        getActivity().setTitle("Các sản phẩm bán chạy");

        allBestSellerAdaper=new AllBestSellerAdaper(getContext());
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        rc_all.setLayoutManager(manager1);
        rc_all.setAdapter(allBestSellerAdaper);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter=new StatisticsPresenterIpl(getActivity(),this);
        presenter.getAllBestSeller();

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
    public void loadAllBest(ItemView itemView) {
        List<ItemBody> list=new ArrayList<>();
        for (ItemBody item: itemView.getItemBodies()
             ) {
            list.add(item);

        }
        allBestSellerAdaper.addModels(list,false);
        txt_total.setText(Utils.formatNumberMoney(itemView.getTotal())+" Đ");
        txt_order.setText(itemView.getAmount()+"");
        txt_products.setText(itemView.getAccountProduct()+"");

    }
}
