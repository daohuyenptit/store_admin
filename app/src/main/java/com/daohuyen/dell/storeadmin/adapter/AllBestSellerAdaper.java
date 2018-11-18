package com.daohuyen.dell.storeadmin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.model.response.Item;
import com.daohuyen.dell.storeadmin.model.response.ItemBody;
import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllBestSellerAdaper extends RecyclerViewAdapter {

    public AllBestSellerAdaper(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view= getInflater().inflate(R.layout.item_allbestseller,parent,false);
        return new AllBestSellerViewHolder(view) ;
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        ItemBody item=  getItem(position, ItemBody.class);
        AllBestSellerViewHolder viewHolder= (AllBestSellerViewHolder) holder;
        viewHolder.tv_stt.setText((position+1)+"");
        viewHolder.tv_name.setText(item.getName());
        viewHolder.tv_account.setText(item.getAmount()+"");

    }

    class  AllBestSellerViewHolder extends NormalViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_account)
        TextView tv_account;
        @BindView(R.id.tv_stt)
        TextView tv_stt;

        public AllBestSellerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
