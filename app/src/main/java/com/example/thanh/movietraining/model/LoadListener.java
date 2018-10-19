package com.example.thanh.movietraining.model;

import java.util.List;

public interface LoadListener {

    void loadSuccess(List<Item> listItem);
    void loadFail(String message);

}
