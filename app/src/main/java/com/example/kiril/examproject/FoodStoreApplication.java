package com.example.kiril.examproject;

import android.app.Application;
import android.content.ContextWrapper;

import com.example.kiril.examproject.authorization.LoginManager;
import com.example.kiril.examproject.models.Category;
import com.example.kiril.examproject.models.ShoppingCar;
import com.example.kiril.examproject.models.User;
import com.facebook.appevents.AppEventsLogger;
import com.orm.SugarContext;
import com.facebook.FacebookSdk;

import java.io.File;

/**
 * Created by Kiril on 05/10/2016.
 */

public class FoodStoreApplication extends Application {
    private LoginManager mLoginManager = new LoginManager();
    private boolean isChecked = true;
    private boolean isServiceRunning = false;

    private void initDbWithCategories(){
        Category pizza = new Category("Pizza", "Best pizza in the world.", R.mipmap.category_pizza);
        Category meat = new Category("Meat", "Rediscover the animal in yourself", R.mipmap.category_meat);
        Category spaghetti  = new Category("Spaghetti", "I love pasta", R.mipmap.category_spaghetti);
        Category salads = new Category("Salads", "Go back to nature", R.mipmap.category_salads);
        Category soups = new Category("Soups", "Get you soup now", R.mipmap.category_soup);

        ShoppingCar car = new ShoppingCar();
        User user = new User("Kiril", "Yankov");

        user.set_car(car);


        pizza.save();
        meat.save();
        spaghetti.save();
        salads.save();
        soups.save();
        car.save();
        user.save();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        //if (doesDatabaseExist(this, "db_foods_store.db")){
        //    SugarDb db = new SugarDb(getApplicationContext());
//
        //    new File(db.getDB().getPath()).delete();
        //}

        this.mLoginManager.setUserIsLogged(false);

        SugarContext.init(getApplicationContext());

        boolean existDb = doesDatabaseExist(this, "db_foods_store.db");

        if (!existDb){
            Category.findById(Category.class, (long)1);

            initDbWithCategories();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    public boolean doesDatabaseExist(ContextWrapper context, String dbName){
        File dbFile = context.getDatabasePath(dbName);

        return dbFile.exists();
    }

    public LoginManager getLoginManager(){
        return this.mLoginManager;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isServiceRunning() {
        return isServiceRunning;
    }

    public void setServiceRunning(boolean serviceRunning) {
        isServiceRunning = serviceRunning;
    }
}
