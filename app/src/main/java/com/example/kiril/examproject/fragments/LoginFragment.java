package com.example.kiril.examproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.kiril.examproject.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

/**
 * Created by Kiril on 08/10/2016.
 */

public class LoginFragment extends BaseUserFragment {
    EditText mUsername;
    EditText mPassword;
    Button mBtnLogin;
    CallbackManager callbackManager;
    Button mLoginButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment, container, false);

        mUsername = (EditText) v.findViewById(R.id.loginFragmentLogin);
        mPassword = (EditText) v.findViewById(R.id.loginFragmentPassword);

        mBtnLogin = (Button) v.findViewById(R.id.loginFragmentBtnLogin);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserListener.login(
                        mUsername.getText().toString(),
                        mPassword.getText().toString());
            }
        });

        mLoginButton = (Button) v.findViewById(R.id.btnFBLogin);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                int a = 5;
                                Log.d("FR", loginResult.toString());
                            }

                            @Override
                            public void onCancel() {
                                // App code
                            }

                            @Override
                            public void onError(FacebookException exception) {
                                // App code
                            }
                        });
            }
        });




        return v;
    }

}
