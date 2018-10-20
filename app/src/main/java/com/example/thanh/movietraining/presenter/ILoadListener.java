package com.example.thanh.movietraining.presenter;

import com.example.thanh.movietraining.model.Movie;

import java.util.ArrayList;
import java.util.List;

public interface ILoadListener {
    void loadSuccess(ArrayList<Movie> listItem);
    void loadFail(String message);
}
