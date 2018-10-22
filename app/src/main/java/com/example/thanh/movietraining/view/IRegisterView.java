package com.example.thanh.movietraining.view;

import com.example.thanh.movietraining.retrofix.model.Registers;

public interface IRegisterView {
    void getDataSuccess(Registers registers);
    void getMessageError(String e);
}
