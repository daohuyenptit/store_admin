package com.daohuyen.dell.store_admin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.daohuyen.dell.store_admin.R;
import com.daohuyen.dell.store_admin.common.Utils;
import com.daohuyen.dell.store_admin.model.view.BillView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerViewAdapter {
    public HistoryAdapter(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view=getInflater().inflate(R.layout.item_history,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        BillView billView=getItem(position,BillView.class);
        HistoryViewHolder historyViewHolder= (HistoryViewHolder) holder;
        historyViewHolder.txt_time.setText(Utils.getTimeAndDate(billView.getCreateDate())+"");
        historyViewHolder.txt_total.setText(Utils.formatNumberMoney(billView.getTotal())+"Đ");
        historyViewHolder.txt_name.setText(billView.getReceiver());
        historyViewHolder.txt_transport.setText(billView.getTransport());
        historyViewHolder.txt_pay.setText(billView.getPay());
        historyViewHolder.txt_address.setText(billView.getAddress_receive());

        if (billView.getPermit()==0){
            historyViewHolder.txt_state.setText("Chưa được duyệt");
        }else{
            historyViewHolder.txt_state.setText("Đã được duyệt");
        }

    }
    class  HistoryViewHolder extends NormalViewHolder {
        @BindView(R.id.txt_time)
        TextView txt_time;
        @BindView(R.id.txt_state)
        TextView txt_state;
        @BindView(R.id.txt_name)
        TextView txt_name;
        @BindView(R.id.txt_total)
        TextView txt_total;
        @BindView(R.id.txt_transport)
        TextView txt_transport;
        @BindView(R.id.txt_pay)
        TextView txt_pay;
        @BindView(R.id.txt_address)
        TextView txt_address;


        public HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
