package com.example.thanh.movietraining.presenter;


import android.util.Log;

import com.example.thanh.movietraining.model.Register;
import com.example.thanh.movietraining.model.User;
import com.example.thanh.movietraining.retrofix.model.Logins;
import com.example.thanh.movietraining.retrofix.model.Registers;
import com.example.thanh.movietraining.view.ILoginView;
import com.example.thanh.movietraining.view.IRegisterView;

public class RegisterPersenter implements IRegisterPresenter {

    private IRegisterView iRegisterView;
    private Register register;

    public RegisterPersenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }

    @Override
    public void getDataSuccess(Registers registers) {
        Log.d("loginlistener","login success...");
        iRegisterView.getDataSuccess(registers);
    }

    @Override
    public void getMessageError(String e) {
        Log.d("loginlistener","login error...");
        iRegisterView.getMessageError(e);
    }

    @Override
    public void onRegister(String email, String full_name, String password, String gender, String birthday) {

        register=new Register(email,full_name,password,gender,birthday,this);
        register.isValiData();

    }


    /*@Override
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
    }*/

}
