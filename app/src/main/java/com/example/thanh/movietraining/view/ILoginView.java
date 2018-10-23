package com.example.thanh.movietraining.view;

import com.example.thanh.movietraining.model.LoginModel;


public interface ILoginView {
    void getDataSuccess(LoginModel logins);
    void getMessageError(String e);
}
