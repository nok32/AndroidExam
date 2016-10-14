package com.example.kiril.examproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by Kiril on 05/10/2016.
 */

public class Category extends SugarRecord implements Parcelable {
    private String _name;
    private int _imageUrl;
    private String _info;

    public Category(){

    }

    public Category(String name, String info, int imageUrl) {
        this.set_name(name);
        this.set_info(info);
        this.set_imageUrl(imageUrl);
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_imageUrl() {
        return _imageUrl;
    }

    public void set_imageUrl(int _imageUrl) {
        this._imageUrl = _imageUrl;
    }

    public String get_info() {
        return _info;
    }

    public void set_info(String _info) {
        this._info = _info;
    }

    protected Category(Parcel in) {
        _name = in.readString();
        _imageUrl = in.readInt();
        _info = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeInt(_imageUrl);
        dest.writeString(_info);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
