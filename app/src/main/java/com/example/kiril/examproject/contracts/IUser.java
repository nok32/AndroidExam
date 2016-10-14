package com.example.kiril.examproject.contracts;

/**
 * Created by Kiril on 08/10/2016.
 */

public interface IUser {
    void login(String name, String password);
    void register(String name, String password, String confirmPassword);
}
