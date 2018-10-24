package com.example.thanh.movietraining.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.library.Converter;
import com.example.library.Files;
import com.example.library.Parses;
import com.example.library.Util;
import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.Sqlite.DBAcount;
import com.example.thanh.movietraining.Utils;
import com.example.thanh.movietraining.adapter.CustomListMovie;
import com.example.thanh.movietraining.object.Movie;
import com.example.thanh.movietraining.presenter.ListPresenter;

import java.util.ArrayList;

import static com.example.thanh.movietraining.Utils.FILE_DATABASE;


public class ListMovieActivity extends AppCompatActivity implements IListMovieView {
    
    private ListView listView;
    private ListPresenter mainPresenter;
    private static CustomListMovie adapter;
    private static int PAGE = 1;
    private static int TOTAL_PAGE;
    private static ArrayList<Movie> movies;
    private static Parses parses;
    private static ArrayList<Integer> integers;
    private Converter converter;
    private static Context context;
    private Button btn_logout;
    private ProgressBar pb_load;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
        //custom title bar ...
        init();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //recoderArrayLike();
        if(BuildConfig.DEBUG){
            Utils.messageDisplay("on destroy...");
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        //recoderArrayLike();
        if(BuildConfig.DEBUG){
            Utils.messageDisplay("on stop...");
        }

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
        context=getApplicationContext();
        listView = findViewById(R.id.list_view);
        mainPresenter = new ListPresenter(this);
        mainPresenter.LoadListView(String.valueOf(PAGE), "10");
        btn_logout=findViewById(R.id.btn_logout);
        pb_load=findViewById(R.id.pb_load);


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                CustomListMovie.isAnimation = true;
                if (!listView.canScrollVertically(1) && scrollState == SCROLL_STATE_IDLE && TOTAL_PAGE == 10) {
                    //recoderArrayLike();
                    PAGE = PAGE + 1;
                    mainPresenter.LoadListView(String.valueOf(PAGE), "10");
                    pb_load.setVisibility(View.VISIBLE);
                }
                if (!listView.canScrollVertically(-1) && scrollState == SCROLL_STATE_IDLE && PAGE > 1) {
                    //recoderArrayLike();
                    PAGE = PAGE - 1;
                    mainPresenter.LoadListView(String.valueOf(PAGE), "10");
                    pb_load.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Files.wirteFile(FILE_DATABASE, new Parses().getBuffer());
                } catch (Exception e) {
                    Utils.messageDisplay("result :" + e.getMessage());
                }
                DBAcount dbManager = new DBAcount(getApplicationContext());
                dbManager.deleteAccount();

                Intent intent = getIntent();
                finish();
                startActivity(intent);

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
    public void ActionLike(int position) {


        if (movies.get(position).isLike() == false) {
            movies.get(position).setLike(true);
            integers.add(Integer.valueOf(movies.get(position).getId()));
        } else {
            movies.get(position).setLike(false);
            integers.remove(Integer.valueOf(movies.get(position).getId()));
        }
        CustomListMovie.isAnimation = false;
        adapter.notifyDataSetChanged();

        //add arrary ...
        recoderArrayLike();
        if(BuildConfig.DEBUG){
            Utils.messageDisplay("on destroy...");
        }


    }

    @Override
    public void showLoading() {

    }
    @Override
    public void hideLoading() {
        pb_load.setVisibility(View.GONE);
    }
    @Override
    public void showError() {

    }



}
