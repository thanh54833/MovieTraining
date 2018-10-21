package com.example.thanh.movietraining.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.adapter.CustomListMovie;
import com.example.thanh.movietraining.model.Movie;
import com.example.thanh.movietraining.presenter.ListPresenter;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class ListMovieActivity extends AppCompatActivity implements ListItem {


    private ListView listView;
    private ListPresenter mainPresenter;
    private static CustomListMovie adapter;
    private static int PAGE = 1;
    private static int TOTAL_PAGE;
    private static ArrayList<Movie> movies;


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
        mainPresenter.loadData(String.valueOf(PAGE), "10");
        //mainPresenter.LoadListView();


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                /*if (!listView.canScrollVertically(1) && scrollState == SCROLL_STATE_IDLE && TOTAL_PAGE == 10) {
                    PAGE = PAGE + 1;
                    mainPresenter.loadData(String.valueOf(PAGE), "10");
                    if (BuildConfig.DEBUG) {
                        Log.d("thanhthanh", "bottom :" + PAGE);
                    }
                }
                if (!listView.canScrollVertically(-1) && scrollState == SCROLL_STATE_IDLE && PAGE > 1) {
                    PAGE = PAGE - 1;
                    mainPresenter.loadData(String.valueOf(PAGE), "10");
                    if (BuildConfig.DEBUG) {
                        Log.d("thanhthanh", "top :" + PAGE);
                    }
                }*/

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
    @Override
    public void displayMain(ArrayList<Movie> list) {

        movies=list;
        adapter = new CustomListMovie(movies, getApplicationContext(),true);
        listView.setAdapter(adapter);

        TOTAL_PAGE = list.size();
        adapter.notifyDataSetChanged();

    }

    public static void ActionLike(int position){

        movies.get(position).setLike(true);

        adapter.notifyDataSetChanged();

    }


}
