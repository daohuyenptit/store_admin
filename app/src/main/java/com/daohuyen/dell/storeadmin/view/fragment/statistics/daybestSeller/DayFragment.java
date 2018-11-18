package com.daohuyen.dell.storeadmin.view.fragment.statistics.daybestSeller;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.adapter.AllBestSellerAdaper;
import com.daohuyen.dell.storeadmin.common.Utils;
import com.daohuyen.dell.storeadmin.custom.LoadingDialog;
import com.daohuyen.dell.storeadmin.model.response.ItemBody;
import com.daohuyen.dell.storeadmin.model.view.ItemView;
import com.daohuyen.dell.storeadmin.presenter.statistics.daybestseller.DayPresenter;
import com.daohuyen.dell.storeadmin.presenter.statistics.daybestseller.DayPresenterIpl;
import com.daohuyen.dell.storeadmin.presenter.statistics.monthbestseller.MonthPresenter;
import com.daohuyen.dell.storeadmin.presenter.statistics.monthbestseller.MonthPresenterIpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayFragment extends Fragment implements DayView {
    LoadingDialog loadingDialog;
    AllBestSellerAdaper allBestSellerAdaper;
    DayPresenter presenter;
    @BindView(R.id.rc_all)
    RecyclerView rc_all;
    @BindView(R.id.txt_products)
    TextView txt_products;
    @BindView(R.id.txt_order)
    TextView txt_order;
    @BindView(R.id.txt_total)
    TextView txt_total;
    @BindView(R.id.txt_time)
    TextView txt_time;
    @BindView(R.id.img_time)
    ImageButton img_time;


    public DayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_day2, container, false);
        ButterKnife.bind(this,view);
        getActivity().setTitle("Sản phẩm bán chạy trong ngày");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        loadingDialog=new LoadingDialog(getContext());
        allBestSellerAdaper=new AllBestSellerAdaper(getContext());
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        rc_all.setLayoutManager(manager1);
        rc_all.setAdapter(allBestSellerAdaper);
        presenter=new DayPresenterIpl(getContext(),this);
        img_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.dialogShowDate(getActivity(), "Chọn thời gian", new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txt_total.setText("0 Đ");
                        txt_order.setText("0");
                        txt_products.setText("0");
                        allBestSellerAdaper.clear();
                        String date = year
                                + "-" +
                                String.format("%02d", (month + 1))
                                +"-"+ String.format("%02d", dayOfMonth)

                        ;
                        String datetime=String.format("%02d", dayOfMonth)+ "-" +
                                String.format("%02d", (month + 1))+"-" +year;
                        txt_time.setText(datetime);
                        presenter.getAllBestSeller(date);

                    }
                });


            }

        });
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
