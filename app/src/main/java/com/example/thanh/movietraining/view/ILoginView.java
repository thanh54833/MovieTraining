package com.example.thanh.movietraining.view;

import com.example.thanh.movietraining.retrofix.model.Logins;

public interface ILoginView {
    void getDataSuccess(Logins logins);
    void getMessageError(String e);
}
