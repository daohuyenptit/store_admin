package com.daohuyen.dell.storeadmin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.model.response.ProductGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductGroupAdapter extends RecyclerViewAdapter {
    public ProductGroupAdapter(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view= getInflater().inflate(R.layout.item_group,parent,false);
        return new ProductGroupHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        ProductGroup productGroup=getItem(position,ProductGroup.class);
        ProductGroupHolder holder1= (ProductGroupHolder) holder;
        holder1.txt_group.setText(productGroup.getName());

    }
    class ProductGroupHolder extends NormalViewHolder{
        @BindView(R.id.txt_group)
        TextView txt_group;


        public ProductGroupHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
