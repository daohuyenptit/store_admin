package com.daohuyen.dell.storeadmin.view.fragment.statistics.monthbestseller;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.adapter.AllBestSellerAdaper;
import com.daohuyen.dell.storeadmin.common.Utils;
import com.daohuyen.dell.storeadmin.custom.LoadingDialog;
import com.daohuyen.dell.storeadmin.model.response.ItemBody;
import com.daohuyen.dell.storeadmin.model.view.ItemView;
import com.daohuyen.dell.storeadmin.presenter.statistics.allbestseller.StatisticsPresenter;
import com.daohuyen.dell.storeadmin.presenter.statistics.monthbestseller.MonthPresenter;
import com.daohuyen.dell.storeadmin.presenter.statistics.monthbestseller.MonthPresenterIpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthFragment extends Fragment implements MonthView{
    LoadingDialog loadingDialog;
    AllBestSellerAdaper allBestSellerAdaper;
    MonthPresenter presenter;
    @BindView(R.id.rc_all)
    RecyclerView rc_all;
    @BindView(R.id.txt_products)
    TextView txt_products;
    @BindView(R.id.txt_order)
    TextView txt_order;
    @BindView(R.id.txt_total)
    TextView txt_total;
    @BindView(R.id.txt_month)
    TextView txt_month;
    @BindView(R.id.txt_year)
    TextView txt_year;
    @BindView(R.id.ln_year)
    LinearLayout ln_year;
    @BindView(R.id.ln_month)
    LinearLayout ln_month;
    @BindView(R.id.img_search)
    ImageButton img_search;


    public MonthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_month, container, false);
        ButterKnife.bind(this,view);
        getActivity().setTitle("Sản phẩm bán chạy trong tháng");
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
        presenter=new MonthPresenterIpl(getContext(),this);
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_month==null){
                    txt_month.setError("Chọn tháng");
                    return;
                }
                if(txt_year==null){
                    txt_year.setError("Chọn năm");
                    return;
                }
                txt_total.setText("0 Đ");
                txt_order.setText("0");
                txt_products.setText("0");

                allBestSellerAdaper.clear();
                String time=txt_year.getText().toString()+"-"+txt_month.getText().toString();
                presenter.getAllBestSeller(time);
            }
        });
        ln_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), ln_month);
                popup.getMenuInflater().inflate(R.menu.menu_month, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.jan:
                                txt_month.setText("1");
                                break;
                            case R.id.feb:
                                txt_month.setText("2");
                                break;
                            case R.id.mar:
                                txt_month.setText("3");
                                break;
                            case R.id.apr:
                                txt_month.setText("4");
                                break;
                            case R.id.may:
                                txt_month.setText("5");
                                break;
                            case R.id.jun:
                                txt_month.setText("6");
                                break;
                            case R.id.jul:
                                txt_month.setText("7");
                                break;
                            case R.id.aug:
                                txt_month.setText("8");
                                break;
                            case R.id.sep:
                                txt_month.setText("9");
                                break;
                            case R.id.oct:
                                txt_month.setText("10");
                                break;
                            case R.id.nov:
                                txt_month.setText("11");
                                break;
                            case R.id.dec:
                                txt_month.setText("12");
                                break;
                        }
                        return false;
                    }
                });
                popup.show();

            }
        });
        ln_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), ln_year);
                popup.getMenuInflater().inflate(R.menu.menu_year, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.year1:
                                txt_year.setText("2018");
                                break;
                            case R.id.year2:
                                txt_year.setText("2017");
                                break;
                            case R.id.year3:
                                txt_year.setText("2016");
                                break;
                            case R.id.year4:
                                txt_year.setText("2015");
                                break;
                            case R.id.year5:
                                txt_year.setText("2014");
                                break;
                            case R.id.year6:
                                txt_year.setText("2013");
                                break;

                        }
                        return false;
                    }
                });
                popup.show();

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
