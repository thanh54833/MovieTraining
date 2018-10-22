package com.example.thanh.movietraining.presenter;


import android.util.Log;

import com.example.thanh.movietraining.retrofix.model.Logins;
import com.example.thanh.movietraining.view.ILoginView;
import com.example.thanh.movietraining.model.User;

public class LoginPersenter implements ILoginPresenter {

    private ILoginView iLoginListenerView;
    private  User user;

    public LoginPersenter(ILoginView iLoginListenerView) {
        this.iLoginListenerView = iLoginListenerView;
    }

    @Override
    public void getDataSuccess(Logins login) {
     Log.d("loginlistener","login success...");
        iLoginListenerView.getDataSuccess(login);
    }

    @Override
    public void getMessageError(String e) {
        Log.d("loginlistener","login error...");
        iLoginListenerView.getMessageError(e);
    }

    @Override
    public void onLogin(String email, String password) {
        user=new User(email,password,this);
        user.isValiData();
    }

}
