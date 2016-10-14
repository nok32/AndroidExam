package com.example.kiril.examproject.controllers;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kiril.examproject.R;
import com.example.kiril.examproject.ShoppingCarActivity;
import com.example.kiril.examproject.contracts.IShoppingCar;
import com.example.kiril.examproject.models.Food;
import com.example.kiril.examproject.models.ShoppingCar;
import com.example.kiril.examproject.models.User;

import java.util.ArrayList;
import java.util.Locale;

import im.delight.android.location.SimpleLocation;

/**
 * Created by Kiril on 10/10/2016.
 */

public class ShoppingCarController extends BaseController implements IShoppingCar {
    private ArrayList<Food> mData;
    private ShoppingCar mCurrentUserShoppingCar;
    private User mCurrentUser;
    private Button mBtnOrder;
    private Button mBtnCall;
    private Button mBtnMap;
    private TextView mOrderPrice;
    private Float mOrderPriceInFloat;
    private SimpleLocation mLocation;

    public ShoppingCarController(Activity activity) {
        super(activity);

        mOrderPrice = (TextView) mActivity.findViewById(R.id.shoppingCarOrderPrice);

        mCurrentUser = mLoginManager.getCurrentUser();

        mCurrentUserShoppingCar = mCurrentUser.get_car();

        mData = mCurrentUserShoppingCar.getAllOrderedFoods();

        mBtnOrder = (Button) activity.findViewById(R.id.btnShoppingCarOrder);
        mBtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeOrder();
            }
        });

        mBtnCall = (Button) mActivity.findViewById(R.id.btnCall);
        mBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });

        mBtnMap = (Button) mActivity.findViewById(R.id.btnOpenMap);
        mBtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewNextRestaurantOnMap();
            }
        });

        mOrderPriceInFloat = calkFoodsPriceInOrder(mData);
        mOrderPrice.setText(String.format("%.2f", mOrderPriceInFloat) + "$");

        mLocation = new SimpleLocation(mActivity);

    }

    public ArrayList<Food> getCurrentUserFoodInShoppingCar() {
        return mData;
    }

    @Override
    public void makeOrder() {
        Toast.makeText(mActivity, "You order is make, have nice day!", Toast.LENGTH_LONG).show();
        for (Food f : mData) {
            f.delete();
        }

        mActivity.finish();
    }

    @Override
    public void viewNextRestaurantOnMap() {
        if (!mLocation.hasLocationEnabled()) {
            // ask the user to enable location access
            SimpleLocation.openSettings(mActivity);
        }

        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", mLocation.getLatitude(), mLocation.getLongitude());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mActivity.startActivity(intent);
    }

    @Override
    public void remove(int position) {
        Food f = this.mData.remove(position);

        this.mOrderPriceInFloat -= f.get_byPrice();
        mOrderPrice.setText(String.format("%.2f", mOrderPriceInFloat) + "$");

        f.delete();

        ((ShoppingCarActivity) mActivity)
                .getShoppingCarAdapter()
                .updateFoodsData();
    }

    @Override
    public void call() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:123456789"));
        boolean havePermission = ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED;
        if (havePermission) {
            mActivity.startActivity(callIntent);
        }else{
            Toast.makeText(mActivity, "This application have no permission to call!", Toast.LENGTH_LONG).show();
        }
    }

    private float calkFoodsPriceInOrder(ArrayList<Food> foods){
        float result = 0;
        for (Food food: foods) {
            result += food.get_byPrice();
        }

        return result;
    }

}
