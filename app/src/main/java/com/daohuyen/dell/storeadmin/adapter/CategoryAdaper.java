package com.daohuyen.dell.storeadmin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.daohuyen.dell.storeadmin.R;
import com.daohuyen.dell.storeadmin.model.view.CategoryViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdaper extends RecyclerViewAdapter {

    public CategoryAdaper(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        View view= getInflater().inflate(R.layout.item_category,parent,false);
        return new CategoryViewHolder(view) ;
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        CategoryViewModel categoryViewModel=  getItem(position, CategoryViewModel.class);
        CategoryViewHolder viewHolder= (CategoryViewHolder) holder;
        viewHolder.tv_category.setText(categoryViewModel.getTitle());
    }

    class  CategoryViewHolder extends NormalViewHolder {
        private ItemClickListener itemClickListener;
        @BindView(R.id.txt_category)
        TextView tv_category;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
