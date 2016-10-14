package com.example.kiril.examproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kiril.examproject.R;
import com.example.kiril.examproject.contracts.IUser;

/**
 * Created by Kiril on 09/10/2016.
 */

public class RegisterFragment extends BaseUserFragment {
    Button mBtnRegister;
    EditText mUsername;
    EditText mPassword;
    EditText mConfirmPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_fragment, container, false);

        mUsername = (EditText) v.findViewById(R.id.registerFragmentUsername);
        mPassword = (EditText) v.findViewById(R.id.registerFragmentPassword);
        mConfirmPassword = (EditText) v.findViewById(R.id.registerFragmentConfirmPassword);

        mBtnRegister = (Button) v.findViewById(R.id.registerFragmentBtnRegister);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserListener.register(
                        mUsername.getText().toString(),
                        mPassword.getText().toString(),
                        mConfirmPassword.getText().toString());
            }
        });

        return v;
    }
}
