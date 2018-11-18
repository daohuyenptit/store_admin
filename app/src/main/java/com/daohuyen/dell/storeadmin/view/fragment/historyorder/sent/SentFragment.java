package com.daohuyen.dell.storeadmin.view.fragment.historyorder.sent;

import android.graphics.Paint;
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
import com.daohuyen.dell.storeadmin.adapter.HistoryAdapter;
import com.daohuyen.dell.storeadmin.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.storeadmin.common.Constants;
import com.daohuyen.dell.storeadmin.common.Utils;
import com.daohuyen.dell.storeadmin.custom.LoadingDialog;
import com.daohuyen.dell.storeadmin.model.view.BillView;
import com.daohuyen.dell.storeadmin.presenter.history.sent.SentBillPresenterIpl;
import com.daohuyen.dell.storeadmin.presenter.history.sent.SentPresenter;
import com.daohuyen.dell.storeadmin.service.eventbus.SendBillView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SentFragment extends Fragment implements SentView,View.OnClickListener,RecyclerViewAdapter.OnItemClickListener {
    @BindView(R.id.rcv_historybill)
    RecyclerView rcv_historybill;
    SentPresenter presenter;
    HistoryAdapter adapter1;
    Set<String> listID=new HashSet<>();
    private Paint p = new Paint();
    List<BillView> listview=new ArrayList<>();
    private LoadingDialog loadingDialog;


    public SentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sent, container, false);
        ButterKnife.bind(this,view);
        EventBus.getDefault().register(this);
        // Inflate the layout for this fragment
        return  view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog= new LoadingDialog(getContext());
        adapter1=new HistoryAdapter(getContext());
        adapter1.removeOnItemClickListener(this);
        adapter1.setSelectedMode(true);
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_historybill.setLayoutManager(manager1);
        rcv_historybill.setAdapter(adapter1);
        presenter=new SentBillPresenterIpl(getContext(),this);
        if(Utils.getSharePreferenceValues(getContext(), Constants.STATUS_LOGIN).equals(Constants.LOGIN_TRUE)){
            presenter.loadAllBills();
        }
    }
    @Override
    public void onDestroy() {
        //  Log.i(TAG, "onDestroy: ");
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnSendingBill(SendBillView sendBillView) {
       List<BillView> list=sendBillView.getList();
        for (int i = 0; i <list.size() ; i++) {
            listview.add(i,list.get(i));

        }
        adapter1.clear();
        adapter1.addModels(listview,false);
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
        this.listview=list;

    }


    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {

    }

    @Override
    public void onClick(View v) {

    }
}
