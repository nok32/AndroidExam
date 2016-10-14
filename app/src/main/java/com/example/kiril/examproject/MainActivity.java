package com.example.kiril.examproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.example.kiril.examproject.contracts.ICategory;
import com.example.kiril.examproject.controllers.FragmentController;
import com.example.kiril.examproject.controllers.UserController;
import com.example.kiril.examproject.fragments.LoginFragment;
import com.example.kiril.examproject.models.Category;
import com.example.kiril.examproject.services.EatService;
import com.example.kiril.examproject.ui.categories.CategoriesAdaptor;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements ICategory {
    private RecyclerView mCategoryRecyclerView;
    private CategoriesAdaptor mCategoryAdapter;
    private ArrayList<Category> mCategoryData;
    private ICategory mCategoryListener = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.menu));


        mCategoryData = (ArrayList<Category>)Category.listAll(Category.class);

        mCategoryRecyclerView = (RecyclerView) findViewById(R.id.categoriesRecyclerView);

        mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCategoryAdapter = new CategoriesAdaptor(this, mCategoryData, mCategoryListener);

        mCategoryRecyclerView.setAdapter(mCategoryAdapter);
    }

    @Override
    public void categoryClick(int position) {
        Intent intent = new Intent(getBaseContext(), FoodsActivity.class);
        intent.putExtra("Category" , this.mCategoryData.get(position));

        startActivity(intent);
    }
}
