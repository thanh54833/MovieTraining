package com.example.thanh.movietraining.presenter;

import com.example.thanh.movietraining.retrofix.model.Registers;

public interface IRegisterPresenter {
    void getDataSuccess(Registers registers);
    void getMessageError(String e);
    void onRegister(String email,String full_name,String password,String gender,String birthday);
}
