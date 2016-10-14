package com.example.kiril.examproject.authorization;

import com.example.kiril.examproject.models.ShoppingCar;
import com.example.kiril.examproject.models.User;

/**
 * Created by Kiril on 06/10/2016.
 */

public class LoginManager{
    private static final String DB_USER_PASSWORD  = "_USER_NAME = ? and _PASSWORD = ?";
    private User mCurrentUser;
    private boolean userIsLogged;
    private String _userName;
    private String _password;

    public LoginManager(){
    }

    public User getCurrentUser() {
        return this.mCurrentUser;
    }

    public void setCurrentUser(User mCurrentUser) {
        this.mCurrentUser = mCurrentUser;
    }

    public boolean isLogged(){
        return this.userIsLogged;
    }

    public void setUserIsLogged(boolean userIsLogged) {
        this.userIsLogged = userIsLogged;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public boolean login(String name, String password){
        boolean result = false;
        if (name.length() > 0 && password.length() > 0) {
            if (User.find(User.class, DB_USER_PASSWORD, name, password).size() > 0){
                User currentUser = User.find(User.class, DB_USER_PASSWORD, name, password).get(0);
                this.set_userName(name);
                this.set_password(password);
                this.setUserIsLogged(true);
                this.setCurrentUser(currentUser);

                result = true;
            }
        }

        return result;
    }

    public boolean register(String name, String password, String confirmPassword){
        boolean result = false;

        if (password.equals(confirmPassword)){
            ShoppingCar car = new ShoppingCar();
            User user = new User(name, password);
            user.set_car(car);

            car.save();
            user.save();
            result = true;
        }

        return result;
    }

    public void logOut(){
        this.setCurrentUser(null);
        this.setUserIsLogged(false);
        this.set_userName(null);
        this.set_password(null);
    }
}
