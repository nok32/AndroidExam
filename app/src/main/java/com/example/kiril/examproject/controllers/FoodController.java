package com.example.kiril.examproject.controllers;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.kiril.examproject.FoodStoreApplication;
import com.example.kiril.examproject.R;
import com.example.kiril.examproject.authorization.LoginManager;
import com.example.kiril.examproject.contracts.IFood;
import com.example.kiril.examproject.fragments.FoodFragment;
import com.example.kiril.examproject.fragments.LoginFragment;
import com.example.kiril.examproject.models.Food;
import com.example.kiril.examproject.models.ShoppingCar;
import com.example.kiril.examproject.models.User;
import com.example.kiril.examproject.request.Requester;
import com.example.kiril.examproject.ui.foods.FoodsAdaptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kiril on 08/10/2016.
 */

public class FoodController extends BaseController implements IFood {
    private static final String KEY_JSON_FOOD_ID = "recipe_id";
    private static final String KEY_JSON_FOOD_TITLE = "title";
    private static final String KEY_JSON_FOOD_IMAGE_URL = "image_url";

    private FoodFragment mFoodFragment;
    private RadioGroup mRadioGroupPortion;
    private Button mBtnAddProductToCar;
    private ArrayList<Food> mFoodsData;
    private FoodsAdaptor mAdapter;
    public Requester requester;

    public FoodController(Activity activity, ArrayList<Food> data, FoodsAdaptor adaptor){
        super(activity);
        mFoodsData = data;
        mAdapter = adaptor;
        requester = new Requester(activity, this);
    }

    @Override
    public void foodClick(int position) {
        String id = mFoodsData.get(position).get_id();

        requester.getFoodByIdRequest(id);
    }

    @Override
    public void getFoods(JSONArray foods) {
        try {
            for (int i = 0; i < foods.length(); i++) {
                JSONObject foodJson = foods.getJSONObject(i);
                Food f = new Food(
                        foodJson.get(KEY_JSON_FOOD_ID).toString(),
                        foodJson.get(KEY_JSON_FOOD_TITLE).toString(),
                        foodJson.get(KEY_JSON_FOOD_IMAGE_URL).toString());
                mFoodsData.add(f);
            }

            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void getFoodById(JSONObject foodAsJson) throws JSONException {
        mFoodFragment = new FoodFragment();
        if (!mFoodFragment.isAdded()){
            mFragmentController.attachFragment(mFoodFragment);

            final Food f = convertJsonFooToFood(foodAsJson);
            mRadioGroupPortion = (RadioGroup) mActivity.findViewById(R.id.radioGroupPortion);

            mBtnAddProductToCar = (Button) mActivity.findViewById(R.id.foodFragmentAddToOrderBtn);
            mBtnAddProductToCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int checkedPortionButton = mRadioGroupPortion.getCheckedRadioButtonId();
                    if (checkedPortionButton == R.id.foodFragmentFoodregular){
                        f.set_byPrice(f.get_regularPortionPrice());
                    }else{
                        f.set_byPrice(f.get_largePortionPrice());
                    }

                    if (mLoginManager.isLogged()){
                        User u = mLoginManager.getCurrentUser();
                        ShoppingCar c = u.get_car();
                        f.set_car(c);
                        f.save();
                        c.save();

                        mFragmentController.dettachFragment(mFoodFragment);
                        Toast.makeText(mActivity, "You added successfuly chosen food to you order", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mActivity, "You must be logged to add this food to order", Toast.LENGTH_LONG).show();
                    }
                }
            });

            mFoodFragment.fillFoodWithData(f);
        }
    }

    private Food convertJsonFooToFood(JSONObject jsonFood) {
        try {
            jsonFood = jsonFood.getJSONObject("recipe");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Food f = null;
        try {
            f = new Food(
                    jsonFood.get(KEY_JSON_FOOD_ID).toString(),
                    jsonFood.get(KEY_JSON_FOOD_TITLE).toString(),
                    jsonFood.get(KEY_JSON_FOOD_IMAGE_URL ).toString()
            );
            JSONArray ingredients = jsonFood.getJSONArray("ingredients");
            for (int i = 0; i < ingredients.length() - 1; i++) {
                if (i != 0){
                    f.set_info(
                            f.get_info() +
                                    ingredients.get(i).toString() + "\n\r");
                }else{
                    f.set_info( ingredients.get(i).toString() + "\n\r");
                }
            }

            Random rnd = new Random();
            int priceDevider = rnd.nextInt(10 - 2) + 2;

            float regularPrice = Float.parseFloat(jsonFood.get("social_rank").toString())  / priceDevider;
            f.set_regularPortionPrice(regularPrice);

            float largePrice = (Float.parseFloat(jsonFood.get("social_rank").toString())  / priceDevider) + 10;
            f.set_largePortionPrice(largePrice);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return f;
    }
}
