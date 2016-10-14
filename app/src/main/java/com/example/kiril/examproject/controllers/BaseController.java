package com.example.kiril.examproject.controllers;

import android.app.Activity;

import com.example.kiril.examproject.FoodStoreApplication;
import com.example.kiril.examproject.authorization.LoginManager;

/**
 * Created by Kiril on 09/10/2016.
 */

public class BaseController {
    protected Activity mActivity;
    protected LoginManager mLoginManager;
    protected FragmentController mFragmentController;

    public BaseController(Activity activity){
        this.mActivity = activity;
        this.mLoginManager = ((FoodStoreApplication)activity.getApplication()).getLoginManager();
        mFragmentController  = new FragmentController(activity);
    }
}
