package com.example.kiril.examproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kiril.examproject.controllers.FoodController;
import com.example.kiril.examproject.models.Category;
import com.example.kiril.examproject.models.Food;
import com.example.kiril.examproject.ui.foods.FoodsAdaptor;

import java.util.ArrayList;

public class FoodsActivity extends BaseActivity{
    ArrayList<Food> mFoodsData;
    private RecyclerView mRecyclerView;
    private FoodsAdaptor mAdapter;
    private FoodController mFoodController;
    private Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        setTitle(R.string.food_from_category);

        mFoodsData = new ArrayList<Food>();

        mRecyclerView = (RecyclerView) findViewById(R.id.foodsRecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FoodsAdaptor(this, mFoodsData);

        mFoodController = new FoodController(this, mFoodsData, mAdapter);

        mAdapter.setListener(mFoodController);

        mRecyclerView.setAdapter(mAdapter);

        Intent intent = getIntent();

        mCategory = intent.getParcelableExtra("Category");

        mFoodController.requester.searchRequest(mCategory.get_name());
    }

}
