package com.example.thanh.movietraining.presenter;

import com.example.thanh.movietraining.retrofix.model.Logins;

public interface ILoginPresenter {
    void getDataSuccess(Logins logins);
    void getMessageError(String e);
    void onLogin(String email, String password);
}
