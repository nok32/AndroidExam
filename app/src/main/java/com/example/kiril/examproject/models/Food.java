package com.example.kiril.examproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by Kiril on 04/10/2016.
 */

public class Food extends SugarRecord implements Parcelable {
    private String _id;
    private String _title;
    private String _imageUrl;
    private String _info;
    private float _regularPortionPrice;
    private float _largePortionPrice;
    private float _byePrice;
    private ShoppingCar _car;

    public Food(){

    }

    public Food(String id, String title, String imageUrl){
        this.set_id(id);
        this.set_title(title);
        this.set_imageUrl(imageUrl);
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_imageUrl() {
        return _imageUrl;
    }

    public void set_imageUrl(String _imageUrl) {
        this._imageUrl = _imageUrl;
    }

    public String get_info() {
        return _info;
    }

    public void set_info(String _info) {
        this._info = _info;
    }

    public float get_regularPortionPrice() {
        return _regularPortionPrice;
    }

    public void set_regularPortionPrice(float _regularPortionPrice) {
        this._regularPortionPrice = _regularPortionPrice;
    }

    public float get_largePortionPrice() {
        return _largePortionPrice;
    }

    public void set_largePortionPrice(float _largePortionPrice) {
        this._largePortionPrice = _largePortionPrice;
    }

    public float get_byPrice() {
        return _byePrice;
    }

    public void set_byPrice(float _byPrice) {
        this._byePrice = _byPrice;
    }

    public ShoppingCar get_car() {
        return _car;
    }

    public void set_car(ShoppingCar _car) {
        this._car = _car;
    }

    protected Food(Parcel in) {
        _id = in.readString();
        _title = in.readString();
        _imageUrl = in.readString();
        _info = in.readString();
        _regularPortionPrice = in.readFloat();
        _largePortionPrice = in.readFloat();
        _byePrice = in.readFloat();
        _car = (ShoppingCar) in.readValue(ShoppingCar.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(_title);
        dest.writeString(_imageUrl);
        dest.writeString(_info);
        dest.writeFloat(_regularPortionPrice);
        dest.writeFloat(_largePortionPrice);
        dest.writeFloat(_byePrice);
        dest.writeValue(_car);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}