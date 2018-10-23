package com.example.thanh.movietraining.view;

import com.example.thanh.movietraining.model.MovieModel;
import com.example.thanh.movietraining.object.Movie;

import java.util.ArrayList;

public interface IListMovie {
     void displayMain(ArrayList<Movie> list);
     void showLoading();
     void hideLoading();
     void showError();
}
