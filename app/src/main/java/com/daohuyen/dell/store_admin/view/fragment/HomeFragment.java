package com.daohuyen.dell.store_admin.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daohuyen.dell.store_admin.R;
import com.daohuyen.dell.store_admin.adapter.HistoryAdapter;
import com.daohuyen.dell.store_admin.common.Constants;
import com.daohuyen.dell.store_admin.common.Utils;
import com.daohuyen.dell.store_admin.custom.LoadingDialog;
import com.daohuyen.dell.store_admin.model.view.BillView;
import com.daohuyen.dell.store_admin.presenter.permitBill.PermitBillPresenterIpl;
import com.daohuyen.dell.store_admin.presenter.permitBill.PermitPresenter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements PermitView,View.OnClickListener {
    @BindView(R.id.rcv_historybill)
    RecyclerView rcv_historybill;
    @BindView(R.id.bt_permit)
    Button bt_permit;
    PermitPresenter presenter;
    HistoryAdapter adapter1;
    Set<String> listID=new HashSet<>();
   List<BillView> listview=new ArrayList<>();
    private LoadingDialog loadingDialog;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        // Inflate the layout for this fragment
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog= new LoadingDialog(getContext());
        adapter1=new HistoryAdapter(getContext(),false);
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_historybill.setLayoutManager(manager1);
        rcv_historybill.setAdapter(adapter1);
        presenter=new PermitBillPresenterIpl(getContext(),this);
        if(Utils.getSharePreferenceValues(getContext(), Constants.STATUS_LOGIN).equals(Constants.LOGIN_TRUE)){
            presenter.loadAllBills();
        }
        bt_permit.setOnClickListener(this);
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
    public void loadAllHistoryBills(List<BillView> list) {
        adapter1.addModels(list,false);
        for (BillView bindView:list){
            listID.add(bindView.getId());

        }

    }

    @Override
    public void loadAllBills() {
        adapter1.clear();
        adapter1.addModels(listview,false);
        adapter1.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_permit:
                presenter.editPermit(listID);
                break;
        }


    }
}
