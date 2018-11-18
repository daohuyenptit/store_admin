package com.daohuyen.dell.storeadmin.view.fragment.statistics.inventory;
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
import android.widget.EditText;
import android.widget.ImageButton;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.adapter.EndlessLoadingRecyclerViewAdapter;
import com.daohuyen.dell.storeadmin.adapter.ProductAdapter;
import com.daohuyen.dell.storeadmin.common.Utils;
import com.daohuyen.dell.storeadmin.model.PageList;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;
import com.daohuyen.dell.storeadmin.presenter.statistics.inventory.InventoryPresenter;
import com.daohuyen.dell.storeadmin.presenter.statistics.inventory.InventoryPresenterIpl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventorProductFragment extends Fragment implements InteratoryView ,
        EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener{// overise của cái này, dùng loadmore, ừa, tại cái presenter k thấy gọi
    @BindView(R.id.recycler_interatory)
    RecyclerView mRecycleView;
    @BindView(R.id.et_time)
    EditText et_time;
    @BindView(R.id.img_time)
    ImageButton img_time;
    InventoryPresenter presenter;
    ProductAdapter adapter;
    String date;


    public InventorProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_inventorproduct, container, false);
        getActivity().setTitle("Sản phẩm tồn kho");
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        adapter = new ProductAdapter(getActivity());
        adapter.setLoadingMoreListener(this);
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(adapter);

        presenter=new InventoryPresenterIpl(getActivity(),this); // cái thí này là chính là interface view của activy này

        //// bên kia gọi this. cái gì đó thì bên này bắt dc,nhung k có chô nào gọi mà
        img_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.dialogShowDate(getActivity(), "Chọn thời gian", new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            date = year
                                + "-" +
                                String.format("%02d", (month + 1))
                                +"-"+ String.format("%02d", dayOfMonth)

                                ;
                        String datetime=String.format("%02d", dayOfMonth)+ "-" +
                                String.format("%02d", (month + 1))+"-" +year;
                        et_time.setText(datetime);
                        presenter.refreshProductPreviews(date);

                    }
                });


            }
        });
    }

    @Override
    public void showLoadMoreProgress() {
        adapter.showLoadingItem(true);
    }

    @Override
    public void hideLoadMoreProgress() {
        adapter.hideLoadingItem();
    }

    @Override
    public void enableLoadMore(boolean enable) {
        adapter.enableLoadingMore(enable);
    }


    @Override
    public void addProductPreviews(PageList<ProductViewModel> productViewModelPageList) {
        adapter.addModels(productViewModelPageList.getResults(),false);

    }

    @Override
    public void refreshProductPreview(PageList<ProductViewModel> productViewModelPageList) {
        adapter.refresh(productViewModelPageList.getResults());
    }


    @Override
    public void onLoadMore() {
        presenter.loadMoreProductPreviews(date);//ok, ơ n tư có à, vợ xóa r mà


    }


}
