package com.example.thanh.movietraining.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.library.Converter;
import com.example.library.Files;
import com.example.library.Parses;
import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.adapter.CustomListMovie;
import com.example.thanh.movietraining.model.Movie;
import com.example.thanh.movietraining.presenter.ListPresenter;

import java.io.File;
import java.util.ArrayList;

import static com.example.thanh.movietraining.Utils.FILE_DATABASE;


public class ListMovieActivity extends AppCompatActivity implements ListItem {


    private ListView listView;
    private ListPresenter mainPresenter;
    private static CustomListMovie adapter;
    private static int PAGE = 1;//1
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
        if(BuildConfig.DEBUG){
            Toast.makeText(getApplicationContext(),"detroy :"+integers,Toast.LENGTH_SHORT).show();
        }
        recoderArrayLike();
        super.onDestroy();
    }

    public void recoderArrayLike(){
        parses=new Parses();
        for(int interger:integers){
            parses.add(parses.getByte(interger));
        }
        parses.Close();

        Log.d("clicklike","result :"+Files.wirteFile(FILE_DATABASE,parses.getBuffer()));
    }

    @Override
    protected void onStop() {
        if(BuildConfig.DEBUG){
            Toast.makeText(getApplicationContext(),"stop",Toast.LENGTH_SHORT).show();
        }
        super.onStop();
    }

    private void init() {

        integers=new ArrayList<>();

        byte[] buffer = Files.readFile(FILE_DATABASE);
        if(buffer!=null){
            converter=new Converter(buffer);
            do {
                integers.add(converter.byteToInterger());
            } while (converter.getBuffer() != 0);
        }
        else {
            if(BuildConfig.DEBUG){
                Toast.makeText(getApplicationContext(),"buffer by null",Toast.LENGTH_SHORT).show();
            }
        }
        Toast.makeText(getApplicationContext(),"length :"+integers.size(),Toast.LENGTH_SHORT).show();

        //parses.Close();*/

        listView = findViewById(R.id.list_view);
        mainPresenter = new ListPresenter(this);
        mainPresenter.loadData(String.valueOf(PAGE), "10");
        //mainPresenter.LoadListView();
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                CustomListMovie.isAnimation = true;
                if (!listView.canScrollVertically(1) && scrollState == SCROLL_STATE_IDLE && TOTAL_PAGE == 10) {
                    recoderArrayLike();
                    PAGE = PAGE + 1;
                    mainPresenter.loadData(String.valueOf(PAGE), "10");
                    if (BuildConfig.DEBUG) {
                        Log.d("thanhthanh", "bottom :" + PAGE);
                    }
                }
                if (!listView.canScrollVertically(-1) && scrollState == SCROLL_STATE_IDLE && PAGE > 1) {
                    recoderArrayLike();
                    PAGE = PAGE - 1;
                    mainPresenter.loadData(String.valueOf(PAGE), "10");
                    if (BuildConfig.DEBUG) {
                        Log.d("thanhthanh", "top :" + PAGE);
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    public void displayMain(ArrayList<Movie> list) {

        movies = list;
        adapter = new CustomListMovie(movies, getApplicationContext(), true);
        listView.setAdapter(adapter);
        TOTAL_PAGE = list.size();
        adapter.notifyDataSetChanged();

    }

    public static   void ActionLike(int position) {

        if(movies.get(position).isLike()==false){
            movies.get(position).setLike(true);
            integers.add(Integer.valueOf(movies.get(position).getId()));
        }else {
            movies.get(position).setLike(false);
            integers.remove(Integer.valueOf(movies.get(position).getId()));
        }

        CustomListMovie.isAnimation = false;
        adapter.notifyDataSetChanged();
        Log.d("clicklike", "result :" + position+" - add :"+integers.size()+"id :"+movies.get(position).getId());
    }


}
