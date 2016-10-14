package com.example.kiril.examproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.kiril.examproject.contracts.IShoppingCar;
import com.example.kiril.examproject.controllers.ShoppingCarController;
import com.example.kiril.examproject.controllers.UserController;
import com.example.kiril.examproject.models.Food;
import com.example.kiril.examproject.ui.categories.CategoriesAdaptor;
import com.example.kiril.examproject.ui.shoppingcar.ShoppingCarAdaptor;

import java.util.ArrayList;

public class ShoppingCarActivity extends AppCompatActivity {

    private RecyclerView mShoppingCarRecyclerView;
    private ShoppingCarAdaptor mShoppingCarAdapter;
    private ArrayList<Food> mCategoryData;
    private ShoppingCarController mShoppingCarController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);

        mShoppingCarController = new ShoppingCarController(this);

        mShoppingCarRecyclerView = (RecyclerView) findViewById(R.id.shoppingCarRecyclerViewId);

        mShoppingCarRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCategoryData = mShoppingCarController.getCurrentUserFoodInShoppingCar();

        mShoppingCarAdapter = new ShoppingCarAdaptor(this, mCategoryData);
        mShoppingCarAdapter.setListener(mShoppingCarController);

        mShoppingCarRecyclerView.setAdapter(mShoppingCarAdapter);
    }

    public ShoppingCarAdaptor getShoppingCarAdapter() {
        return mShoppingCarAdapter;
    }
}
