package com.example.kiril.examproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by Kiril on 05/10/2016.
 */

public class User extends SugarRecord implements Parcelable {
    private String _userName;
    private String _password;
    private ShoppingCar _car;

    public User() {

    }

    public User(String userName, String password) {
        this.set_userName(userName);
        this.set_password(password);
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        if (_userName.length() < 2){
            throw new IllegalArgumentException("Username must be at least two symbols!");
        }else{
            this._userName = _userName;
        }
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        if (_password.length() < 2){
            throw new IllegalArgumentException("The password must be at least two symbols!");
        }else{
            this._password = _password;
        }
    }

    public ShoppingCar get_car() {
        return _car;
    }

    public void set_car(ShoppingCar _car) {
        this._car = _car;
    }

    protected User(Parcel in) {
        _userName = in.readString();
        _password = in.readString();
        _car = (ShoppingCar) in.readValue(ShoppingCar.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_userName);
        dest.writeString(_password);
        dest.writeValue(_car);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
