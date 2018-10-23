package com.example.thanh.movietraining.view;

import com.example.thanh.movietraining.model.RegisterModel;

public interface IRegisterView {
    void getDataSuccess(RegisterModel registers);
    void getMessageError(String e);
}
