package com.example.kiril.examproject.ui.foods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kiril.examproject.R;
import com.example.kiril.examproject.contracts.IFood;
import com.example.kiril.examproject.models.Food;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Kiril on 04/10/2016.
 */

public class FoodsAdaptor extends RecyclerView.Adapter<FoodsAdaptor.VideoHolder>{
    private Context mCxt;
    private ArrayList<Food> mAdapterData;
    private IFood mListener;


    public FoodsAdaptor(Context context, ArrayList<Food> data){
        mCxt = context;
        mAdapterData = data;
    }

    public void setListener(IFood listener){
        mListener = listener;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_template, parent, false);

        VideoHolder vh = new VideoHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        if (holder != null) {
            holder.mFoodTitle.setText(this.mAdapterData.get(position).get_title());
            Picasso.with(mCxt)
                    .load(
                            this.mAdapterData.get(position)
                                    .get_imageUrl())
                    .into(holder.mFoodImage);

            holder.setItemPosition(position);
        }
    }

    @Override
    public int getItemCount() {
        return this.mAdapterData.size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder{

        TextView mFoodTitle;
        ImageView mFoodImage;
        int position;

        public void setItemPosition(int position) {
            this.position = position;
        }

        public VideoHolder(View itemView) {
            super(itemView);

            this.mFoodTitle = (TextView) itemView.findViewById(R.id.foodTitle);
            this.mFoodImage = (ImageView) itemView.findViewById(R.id.foodPic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.foodClick(position);
                }
            });
        }
    }

}

