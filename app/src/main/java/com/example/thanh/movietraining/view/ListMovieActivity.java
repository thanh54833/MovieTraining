package com.example.thanh.movietraining.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.adapter.CustomListMovie;
import com.example.thanh.movietraining.model.Movie;
import com.example.thanh.movietraining.presenter.ListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ListMovieActivity extends AppCompatActivity implements ListItem {

    private ListView listView;
    private ListPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);

        //custom title bar ...
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        getSupportActionBar().getCustomView();

        listView = findViewById(R.id.list_view);

        mainPresenter = new ListPresenter(this);

        mainPresenter.loadData("1","5");

    }

    @Override
    public void displayMain(ArrayList<Movie> list) {

        //listView.setAdapter(new ArrayAdapter<Movie>(this,android.R.layout.simple_list_item_1,list));
        CustomListMovie adapter = new CustomListMovie(list, getApplicationContext());
        listView.setAdapter(adapter);

    }

}
