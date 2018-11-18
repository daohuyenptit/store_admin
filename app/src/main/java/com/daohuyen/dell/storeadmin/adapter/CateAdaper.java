package com.daohuyen.dell.storeadmin.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.common.Constants;
import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;
import com.daohuyen.dell.storeadmin.view.product_category.Product_CateoryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CateAdaper extends RecyclerViewAdapter {

    public CateAdaper(Context context, boolean enableSelectedMode) {
        super(context, enableSelectedMode);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view= getInflater().inflate(R.layout.item_cate,parent,false);
        return new CategoryViewHolder(view) ;
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        CategoryViewModel categoryViewModel=  getItem(position, CategoryViewModel.class);
        CategoryViewHolder viewHolder= (CategoryViewHolder) holder;
        Glide.with(getContext())
                .load(categoryViewModel.getLogo())
                .apply(new RequestOptions().placeholder(R.drawable.br_account)).into(viewHolder.img_avatar);

        viewHolder.tv_category.setText(categoryViewModel.getTitle());
    }

    class  CategoryViewHolder extends NormalViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private ItemClickListener itemClickListener;
        @BindView(R.id.img_avatar)
        ImageView img_avatar;
        @BindView(R.id.tv_category)
        TextView tv_category;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            ButterKnife.bind(this, itemView);
        }


        @Override
        public void onClick(View v) {
            Intent intent= new Intent(getContext(), Product_CateoryActivity.class);
            intent.putExtra(Constants.CATEGORY_ID,  getItem(getPosition(),CategoryViewModel.class).getId());
            intent.putExtra(Constants.CATEGORY_NAME,  getItem(getPosition(),CategoryViewModel.class).getTitle());
            getContext().startActivity(intent);

        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }
}
