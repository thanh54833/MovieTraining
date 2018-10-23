package com.example.thanh.movietraining.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.library.Converter;
import com.example.library.Files;
import com.example.library.Parses;
import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.Utils;
import com.example.thanh.movietraining.adapter.CustomListMovie;
import com.example.thanh.movietraining.object.Movie;
import com.example.thanh.movietraining.presenter.ListPresenter;
import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.util.ArrayList;

import static com.example.thanh.movietraining.Utils.FILE_DATABASE;


public class ListMovieActivity extends AppCompatActivity implements IListMovie {
    
    private ListView listView;
    private ListPresenter mainPresenter;
    private static CustomListMovie adapter;
    private static int PAGE = 1;
    private static int TOTAL_PAGE;
    private static ArrayList<Movie> movies;
    private static Parses parses;
    private static ArrayList<Integer> integers;
    private Converter converter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
        //custom title bar ...
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        getSupportActionBar().getCustomView();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recoderArrayLike();
        if(BuildConfig.DEBUG){
            Utils.messageDisplay("on destroy...");
        }
        Toast.makeText(getApplicationContext(),"on destroy..",Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(BuildConfig.DEBUG){
            Utils.messageDisplay("on stop...");
        }
        Toast.makeText(getApplicationContext(),"on stop..",Toast.LENGTH_SHORT).show();
    }

    public void recoderArrayLike() {
        parses = new Parses();
        for (int interger : integers) {
            parses.add(parses.getByte(interger));
        }
        try {
            Files.wirteFile(FILE_DATABASE, parses.getBuffer());
        } catch (Exception e) {
            Utils.messageDisplay("result :" + e.getMessage());
        }
        parses.Close();
    }

    private void init() {
        integers = new ArrayList<>();
        LoadArrayLike();
        listView = findViewById(R.id.list_view);
        mainPresenter = new ListPresenter(this);
        mainPresenter.LoadListView(String.valueOf(PAGE), "10");
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                CustomListMovie.isAnimation = true;
                if (!listView.canScrollVertically(1) && scrollState == SCROLL_STATE_IDLE && TOTAL_PAGE == 10) {
                    recoderArrayLike();
                    PAGE = PAGE + 1;
                    mainPresenter.LoadListView(String.valueOf(PAGE), "10");
                }
                if (!listView.canScrollVertically(-1) && scrollState == SCROLL_STATE_IDLE && PAGE > 1) {
                    recoderArrayLike();
                    PAGE = PAGE - 1;
                    mainPresenter.LoadListView(String.valueOf(PAGE), "10");
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
    private void LoadArrayLike() {
        if (Files.readFile(FILE_DATABASE) == null) {
            try {
                Files.wirteFile(FILE_DATABASE, new Parses().getBuffer());
            } catch (Exception e) {
                Utils.messageDisplay("result :" + e.getMessage());
            }
        }
        byte[] buffer = Files.readFile(FILE_DATABASE);
        if (buffer.length != 0) {
            converter = new Converter(buffer);
            do {
                integers.add(converter.byteToInterger());
            } while (converter.getBuffer() != 0);
        } else {
            Utils.messageDisplay("buffer by null ");
        }
    }
    @Override
    public void displayMain(ArrayList<Movie> list) {
        movies = list;
        adapter = new CustomListMovie(movies, getApplicationContext(), true);
        listView.setAdapter(adapter);
        TOTAL_PAGE = list.size();
        adapter.notifyDataSetChanged();
        //Toast.makeText(getApplicationContext(), "length :" + list.size(), Toast.LENGTH_SHORT).show();
    }
    public static void ActionLike(int position) {
        if (movies.get(position).isLike() == false) {
            movies.get(position).setLike(true);
            integers.add(Integer.valueOf(movies.get(position).getId()));
        } else {
            movies.get(position).setLike(false);
            integers.remove(Integer.valueOf(movies.get(position).getId()));
        }
        CustomListMovie.isAnimation = false;
        adapter.notifyDataSetChanged();
    }
    @Override
    public void showLoading() {

    }
    @Override
    public void hideLoading() {

    }
    @Override
    public void showError() {

    }

}
