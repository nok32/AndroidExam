package com.example.kiril.examproject.ui.shoppingcar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kiril.examproject.R;
import com.example.kiril.examproject.contracts.IShoppingCar;
import com.example.kiril.examproject.models.Food;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Kiril on 10/10/2016.
 */

public class ShoppingCarAdaptor extends RecyclerView.Adapter<ShoppingCarAdaptor.VideoHolder>{
    private Context mCxt;
    private ArrayList<Food> mAdapterData;
    private IShoppingCar mListener;


    public ShoppingCarAdaptor(Context context, ArrayList<Food> data){
        mCxt = context;
        mAdapterData = data;
    }

    public void setListener(IShoppingCar listener){
        mListener = listener;
    }

    public void updateFoodsData(){
            notifyDataSetChanged();
    }

    @Override
    public ShoppingCarAdaptor.VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shopping_car_template, parent, false);

        ShoppingCarAdaptor.VideoHolder vh = new ShoppingCarAdaptor.VideoHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ShoppingCarAdaptor.VideoHolder holder, int position) {
        if (holder != null) {
            holder.mFoodTitle.setText(this.mAdapterData.get(position).get_title());
            Picasso.with(mCxt)
                    .load(
                            this.mAdapterData.get(position)
                                    .get_imageUrl())
                    .into(holder.mFoodImage);
            holder.mFoodPrice.setText(String.format( "%.2f", mAdapterData.get(position).get_byPrice()) + "$" );

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
        TextView mFoodPrice;
        Button mBtnFoodRemove;
        int position;

        public void setItemPosition(int position) {
            this.position = position;
        }

        public VideoHolder(View itemView) {
            super(itemView);

            this.mFoodTitle = (TextView) itemView.findViewById(R.id.shoppingCarFoodTitle);
            this.mFoodPrice = (TextView) itemView.findViewById(R.id.shoppingCarfoodPrice);
            this.mFoodImage = (ImageView) itemView.findViewById(R.id.shoppingCarFoodPic);
            mBtnFoodRemove = (Button) itemView.findViewById(R.id.btnShoppingCarRemove);
            mBtnFoodRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.remove(position);
                }
            });
        }
    }

}
