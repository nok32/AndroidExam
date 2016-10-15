package com.example.kiril.examproject.controllers;

import android.app.Activity;
import android.widget.Toast;

import com.example.kiril.examproject.contracts.IUser;
import com.example.kiril.examproject.fragments.LoginFragment;
import com.example.kiril.examproject.fragments.RegisterFragment;
import com.example.kiril.examproject.models.User;

/**
 * Created by Kiril on 09/10/2016.
 */

public class UserController extends BaseController implements IUser {
    private LoginFragment mLoginFragment;
    private RegisterFragment mRegisterFragment;

    public UserController(Activity activity){
        super(activity);
    }

    public void setLoginFragment(LoginFragment loginFragment){
        this.mLoginFragment = loginFragment;
    }

    public void setRegisterFragment(RegisterFragment registerFragment){
        this.mRegisterFragment = registerFragment;
    }

    @Override
    public void login(String name, String password) {
        boolean resultFromLogin = mLoginManager.login(name, password);
        if (resultFromLogin){
            Toast.makeText(mActivity.getApplicationContext(), "You logged in successfuly!", Toast.LENGTH_SHORT).show();
            mFragmentController.dettachFragment(mLoginFragment);
        }else{
            Toast.makeText(mActivity.getApplicationContext(), "You failed to logged in, please try again!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void register(String name, String password, String confirmPassword) {
        try{
            boolean registerResult = mLoginManager.register(name, password, confirmPassword);
            if (registerResult == true){
                mLoginManager.login(name, password);
                mFragmentController.dettachFragment(mRegisterFragment);
            }else{
                Toast.makeText(mActivity, "The registration process is unsuccessfuly, please try again to register!", Toast.LENGTH_LONG).show();
                mFragmentController.dettachFragment(mRegisterFragment);
            }
        }catch (IllegalArgumentException e){
            Toast.makeText(mActivity, "The registration process is unsuccessfuly, " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void logOut(){
        mLoginManager.logOut();
        Toast.makeText(mActivity.getApplicationContext(), "You log out successfuly!", Toast.LENGTH_SHORT).show();
    }

    public User getCurrentUser(){
        return mLoginManager.getCurrentUser();
    }
}
