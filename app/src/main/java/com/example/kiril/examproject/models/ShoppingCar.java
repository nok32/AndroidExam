package com.example.kiril.examproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kiril on 05/10/2016.
 */

public class ShoppingCar extends SugarRecord {
    private static final String DB_FOOD_CAR = "_CAR = ?";
    public ShoppingCar() {
    }

    public ArrayList<Food> getAllOrderedFoods(){
        ArrayList<Food> foods = (ArrayList<Food>) Food.find(Food.class, DB_FOOD_CAR, getId().toString());
        return foods;
    }

}
