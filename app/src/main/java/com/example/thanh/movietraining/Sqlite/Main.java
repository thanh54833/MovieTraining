package com.example.thanh.movietraining.Sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.retrofix.model.Logins;
import com.example.thanh.movietraining.retrofix.model.Movies;


import java.util.List;

public class Main extends AppCompatActivity {

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);

        Logins.Datas  datas=new Logins.Datas("thanh","thanh","thanh","thanh","thanh","thanh","thanh","632","thanh","thanh","thanh");
        Logins log=new Logins("thanh","thanh",datas,"thanh");

        dbManager.addAccount(log);

        Toast.makeText(this,"total :"+dbManager.getAccount(),Toast.LENGTH_SHORT).show();
        Log.d("thanhthanh","total:"+dbManager.getAccount().getError());
    }








}
