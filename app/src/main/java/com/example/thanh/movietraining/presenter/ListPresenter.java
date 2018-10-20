package com.example.thanh.movietraining.presenter;



import android.util.Log;

import com.example.thanh.movietraining.model.Movie;
import com.example.thanh.movietraining.model.UserInterator;
import com.example.thanh.movietraining.view.ListItem;

import java.util.ArrayList;
import java.util.List;

public class ListPresenter implements ILoadListener {

    private static  final String  Tag="movietraining.presenter";
    private ListItem listItem;
    private UserInterator userInterator;

    public ListPresenter(ListItem listItem) {
        this.listItem = listItem;
    }

    public void loadData(String page,String per_page){
        userInterator = new UserInterator(page,per_page,this);
        userInterator.createListData();
    }

    @Override
    public void loadSuccess(ArrayList<Movie> list) {
        listItem.displayMain(list);
    }

    @Override
    public void loadFail(String message) {
        Log.d(Tag,"Error :"+message);
    }
}
