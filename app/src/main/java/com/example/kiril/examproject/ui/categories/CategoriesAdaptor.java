package com.example.kiril.examproject.ui.categories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kiril.examproject.R;
import com.example.kiril.examproject.contracts.ICategory;
import com.example.kiril.examproject.models.Category;

import java.util.ArrayList;

/**
 * Created by Kiril on 05/10/2016.
 */

public class CategoriesAdaptor extends RecyclerView.Adapter<CategoriesAdaptor.VideoHolder>{
    private Context mCxt;
    private ArrayList<Category> mAdapterData;
    private ICategory mListener;


    public CategoriesAdaptor(Context context, ArrayList<Category> data, ICategory listener){
        mCxt = context;
        mAdapterData = data;
        mListener = listener;
    }

    public void updateFoodsData(ArrayList<Category> newFoodsData){
        if (mAdapterData.size() == 0){
            this.mAdapterData.addAll(newFoodsData);

            notifyDataSetChanged();
        }
    }

    @Override
    public CategoriesAdaptor.VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_template, parent, false);

        CategoriesAdaptor.VideoHolder vh = new CategoriesAdaptor.VideoHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(CategoriesAdaptor.VideoHolder holder, int position) {
        if (holder != null) {
            holder.mCategoryTitle.setText(this.mAdapterData.get(position).get_name());
            holder.mCategoryImage.setImageResource(this.mAdapterData.get(position).get_imageUrl());
            holder.mCategoryInfo.setText(this.mAdapterData.get(position).get_info());

            holder.setItemPosition(position);
        }
    }

    @Override
    public int getItemCount() {
        return this.mAdapterData.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder{

        TextView mCategoryTitle;
        ImageView mCategoryImage;
        TextView mCategoryInfo;
        int position;

        public void setItemPosition(int position) {
            this.position = position;
        }

        public VideoHolder(final View itemView) {
            super(itemView);

            this.mCategoryTitle = (TextView) itemView.findViewById(R.id.categoryTitle);
            this.mCategoryImage = (ImageView) itemView.findViewById(R.id.categoryPic);
            this.mCategoryInfo = (TextView) itemView.findViewById(R.id.categoryInfo);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.categoryClick(position);
                }
            });
        }
    }

}

