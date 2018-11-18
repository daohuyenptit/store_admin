package com.daohuyen.dell.storeadmin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.common.Utils;
import com.daohuyen.dell.storeadmin.model.view.BillView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerViewAdapter implements RecyclerViewAdapter.OnItemClickListener,RecyclerViewAdapter.OnItemSelectionChangedListener {
    public HistoryAdapter(Context context) {
        super(context, false);
        setOnItemSelectionChangeListener(this);
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

    }

    @Override
    public void setSelectedMode(boolean isSelected) {
        super.setSelectedMode(isSelected);
        if (isSelected) {
            backup();
            addOnItemClickListener(this);
            notifyItemRangeChanged(0, getItemCount());
        } else {
            removeOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, RecyclerView.ViewHolder viewHolder, int viewType, int position) {
        if (isInSelectedMode() && viewType == VIEW_TYPE_NORMAL) {
            setSelectedItem(position, !isItemSelected(position));
        }

    }

    @Override
    public void onItemSelectionChanged(RecyclerView.ViewHolder viewHolder, int viewType, boolean isSelected) {
        if (viewType == VIEW_TYPE_NORMAL) {
            HistoryViewHolder historyViewHolder = (HistoryViewHolder) viewHolder;
            if (isSelected) {
                historyViewHolder.itemView.setBackgroundColor(getContext().getResources().getColor(R.color.light_gray));
                historyViewHolder.img_select.setVisibility(View.VISIBLE);
            } else {
                TypedValue outValue=new TypedValue();
                getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground,outValue,true);
                historyViewHolder.itemView.setBackgroundResource(outValue.resourceId);
                historyViewHolder.img_select.setVisibility(View.GONE);

            }
        }

    }

    class  HistoryViewHolder extends NormalViewHolder {
        @BindView(R.id.txt_time)
        TextView txt_time;
        @BindView(R.id.img_select)
        ImageButton img_select;
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
