package com.example.kiril.examproject;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kiril.examproject.controllers.FragmentController;
import com.example.kiril.examproject.controllers.UserController;
import com.example.kiril.examproject.fragments.LoginFragment;
import com.example.kiril.examproject.fragments.RegisterFragment;
import com.example.kiril.examproject.services.EatService;

public class BaseActivity extends AppCompatActivity {
    protected LoginFragment mLoginFragment;
    protected RegisterFragment mRegisterFragment;
    protected UserController mUserController;
    protected FragmentController mFragmentController;
    protected Intent eatServiceIntent;
    MenuItem mEatServiceRun;
    protected FoodStoreApplication app;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        mEatServiceRun = menu.findItem(R.id.checkable_menu);
        mEatServiceRun.setChecked(app.isChecked());
        eatServiceIntent = new Intent(this, EatService.class);
        if (app.isChecked() && !app.isServiceRunning()){
            startService(eatServiceIntent);
            app.setServiceRunning(true);
        }

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuLogin:
                showLoginForm();
                return true;
            case R.id.menuRegister:
                showRegisterForm();
                return true;
            case R.id.menuCart:
                goToCart();
                return true;
            case R.id.menuLogOut:
                logOut();
                return true;
            case R.id.checkable_menu:
                app.setChecked(!app.isChecked());
                mEatServiceRun.setChecked(app.isChecked());

                if (app.isChecked()&& !app.isServiceRunning()){
                    startService(eatServiceIntent);
                    app.setServiceRunning(true);
                }else if(!app.isChecked()){
                    stopService(eatServiceIntent);
                    app.setServiceRunning(false);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToCart() {
        if (mUserController.getCurrentUser() != null){
            Intent intent = new Intent(this, ShoppingCarActivity.class);

            startActivity(intent);
        }else{
            Toast.makeText(this,"You must be login first to go to you shopping car!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app =  (FoodStoreApplication) getApplication();

        mLoginFragment = new LoginFragment();

        mRegisterFragment = new RegisterFragment();

        mUserController = new UserController(this);

        mFragmentController = new FragmentController(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void showLoginForm() {
        mFragmentController.dettachFragment(mRegisterFragment);

        mUserController.setLoginFragment(mLoginFragment);
        mLoginFragment.setListener(mUserController);

        mFragmentController.attachFragment(mLoginFragment);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void showRegisterForm() {
        mFragmentController.dettachFragment(mLoginFragment);

        mRegisterFragment.setListener(mUserController);
        mUserController.setRegisterFragment(mRegisterFragment);

        mFragmentController.attachFragment(mRegisterFragment);
    }

    protected void logOut(){
        mUserController.logOut();
    }
}
