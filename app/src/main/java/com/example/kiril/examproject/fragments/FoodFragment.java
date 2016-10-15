package com.example.kiril.examproject.fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.kiril.examproject.R;
import com.example.kiril.examproject.controllers.FragmentController;
import com.example.kiril.examproject.models.Food;
import com.squareup.picasso.Picasso;

/**
 * Created by Kiril on 06/10/2016.
 */

public class FoodFragment extends Fragment {
    private ImageView mFoodImage;
    private TextView mFoodTitle;
    private TextView mFoodInfo;
    private RadioButton mRegularPortion;
    private RadioButton mLargePortion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.food_fragment, container, false);

        mFoodImage = (ImageView) v.findViewById(R.id.foodFragmentFoodImage);
        mFoodTitle = (TextView) v.findViewById(R.id.foodFragmentFoodTitle);
        mFoodInfo = (TextView) v.findViewById(R.id.foodFragmentFoodInfo);
        mRegularPortion = (RadioButton) v.findViewById(R.id.foodFragmentFoodregular);
        mLargePortion = (RadioButton) v.findViewById(R.id.foodFragmentFoodLarge);

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void fillFoodWithData(Food f){
        Picasso.with(getContext())
                .load(f.get_imageUrl())
                .into(mFoodImage);
        mFoodTitle.setText(f.get_title());
        mFoodInfo.setText(f.get_info());
        mRegularPortion.setText("Regular portion: " + String.format("%.2f", f.get_regularPortionPrice()) + "$");
        mLargePortion.setText("Large portion: " + String.format("%.2f" , f.get_largePortionPrice()) + "$");
    }
}
