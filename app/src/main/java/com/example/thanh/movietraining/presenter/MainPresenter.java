package com.example.thanh.movietraining.presenter;



import com.example.thanh.movietraining.model.Item;
import com.example.thanh.movietraining.model.LoadListener;
import com.example.thanh.movietraining.model.UserInterator;
import com.example.thanh.movietraining.view.ListItem;

import java.util.List;

public class MainPresenter implements LoadListener {

    private ListItem listItem;
    private UserInterator userInterator;


    public MainPresenter(ListItem listItem) {
        this.listItem = listItem;
        userInterator = new UserInterator(this);

    }

    public void loadData(){

        userInterator.createListData();
    }

    @Override
    public void loadSuccess(List<Item> list) {
         listItem.displayMain(list);
    }

    @Override
    public void loadFail(String message) {

    }
}
