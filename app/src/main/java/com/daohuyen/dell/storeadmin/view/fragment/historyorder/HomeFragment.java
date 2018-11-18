package com.daohuyen.dell.storeadmin.view.fragment.historyorder;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.adapter.HistoryAdapter;
import com.daohuyen.dell.storeadmin.adapter.RecyclerViewAdapter;
import com.daohuyen.dell.storeadmin.common.Constants;
import com.daohuyen.dell.storeadmin.common.Utils;
import com.daohuyen.dell.storeadmin.custom.LoadingDialog;
import com.daohuyen.dell.storeadmin.model.view.BillView;
import com.daohuyen.dell.storeadmin.presenter.history.permitBill.PermitBillPresenterIpl;
import com.daohuyen.dell.storeadmin.presenter.history.permitBill.PermitPresenter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements PermitView,View.OnClickListener,RecyclerViewAdapter.OnItemClickListener {
    @BindView(R.id.rcv_historybill)
    RecyclerView rcv_historybill;
    @BindView(R.id.bt_permit)
    Button bt_permit;
    PermitPresenter presenter;
    HistoryAdapter adapter1;
    Set<String> listID=new HashSet<>();
    private Paint p = new Paint();
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
        adapter1=new HistoryAdapter(getContext());
        adapter1.removeOnItemClickListener(this);
        adapter1.setSelectedMode(true);
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_historybill.setLayoutManager(manager1);
        rcv_historybill.setAdapter(adapter1);
        presenter=new PermitBillPresenterIpl(getContext(),this);
        if(Utils.getSharePreferenceValues(getContext(), Constants.STATUS_LOGIN).equals(Constants.LOGIN_TRUE)){
            presenter.loadAllBills();
        }
        bt_permit.setOnClickListener(this);
        initSwipe();
    }
    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    presenter.deleteBill(adapter1.getItem(position, BillView.class).getId());
//                    adapter1.removeModel(position);
//                    adapter1.notifyItemRemoved(position);
                    listview.remove(position);
                    adapter1.notifyItemRangeChanged(position, listview.size());
                }
//                } else {
//                    Toast.makeText(getContext(), "Bạn hay sửa đi", Toast.LENGTH_SHORT).show();
//                }

            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0) {
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit_white);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);

                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_white);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rcv_historybill);
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
    public void deleteAllSelect() {
        adapter1.removeAllSelectedItems();
    }

    @Override
    public void loadAllHistoryBills(List<BillView> list) {
        adapter1.addModels(list,false);
        if (list.size()==0){
            bt_permit.setVisibility(View.GONE);
        }else{
            bt_permit.setVisibility(View.VISIBLE);

        }
        this.listview=list;

    }

    @Override
    public void loadAllBills() {
        adapter1.clear();
        adapter1.addModels(listview,false);
        if (listview.size()==0){
            bt_permit.setVisibility(View.GONE);
        }else{
            bt_permit.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_permit:
                for (int i = 0; i < adapter1.getItemCount(); i++) {
                    if (adapter1.isItemSelected(i)) {
                        listID.add(adapter1.getItem(i,BillView.class).getId());
                        listview.remove(i);
                    }
                }
                presenter.editPermit(listID);
                deleteAllSelect();
                break;
        }


    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {

    }
}
