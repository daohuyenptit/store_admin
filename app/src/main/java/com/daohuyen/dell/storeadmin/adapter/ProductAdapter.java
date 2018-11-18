package com.daohuyen.dell.storeadmin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.common.Utils;
import com.daohuyen.dell.storeadmin.model.view.ProductViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends EndlessLoadingRecyclerViewAdapter {

    public ProductAdapter(Context context) {
        super(context, false);
    }
    @Override
    protected RecyclerView.ViewHolder initLoadingViewHolder(ViewGroup parent) {
        View loadingView = getInflater().inflate(R.layout.item_load_more, parent, false);
        return new LoadingViewHolder(loadingView);
    }

    @Override
    protected void bindLoadingViewHolder(LoadingViewHolder loadingViewHolder, int position) {

    }
    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view=getInflater().inflate(R.layout.item_product,parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        ProductViewModel productViewModel=  getItem(position, ProductViewModel.class);
        ProductAdapter.ProductViewHolder viewHolder= (ProductAdapter.ProductViewHolder) holder;
        Glide.with(getContext())
                .load(productViewModel.getLogoUrl())
                .apply(new RequestOptions().placeholder(R.drawable.br_account)).into(viewHolder.img_logoProduct);

        viewHolder.tv_name_product.setText(productViewModel.getName());
        viewHolder.tv_price.setText(Utils.formatNumberMoney(productViewModel.getPrice())+" "+"Đ");

    }
    class  ProductViewHolder extends NormalViewHolder{
        @BindView(R.id.img_logoProduct)
        ImageView img_logoProduct;
        @BindView(R.id.tv_name_product)
        TextView tv_name_product;
        @BindView(R.id.tv_price)
        TextView tv_price;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
