package com.example.thanh.movietraining.model;

import java.util.ArrayList;
import java.util.List;

public class UserInterator {
    private LoadListener loadListener;

    public UserInterator(LoadListener loadListener) {
        this.loadListener = loadListener;
    }

    public void createListData(){

        List<Item> list = new ArrayList<>();
        for (int i = 0 ; i < 5; i++){
            Item item = new Item("item number :"+i);
            list.add(item);
        }
        loadListener.loadSuccess(list);


    }
}
