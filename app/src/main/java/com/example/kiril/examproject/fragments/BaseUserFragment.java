package com.example.kiril.examproject.fragments;

import android.app.Fragment;

import com.example.kiril.examproject.contracts.IUser;

/**
 * Created by Kiril on 09/10/2016.
 */

public class BaseUserFragment extends Fragment {
    IUser mUserListener;

    public void setListener(IUser listener){
        this.mUserListener = listener;
    }
}
