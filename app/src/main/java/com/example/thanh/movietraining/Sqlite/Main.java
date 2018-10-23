package com.example.thanh.movietraining.Sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.thanh.movietraining.R;

public class Main extends AppCompatActivity {

    //private DBAcount dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*dbManager = new DBAcount(this);

        Logins.Datas  datas=new Logins.Datas("thanh","thanh","thanh","thanh","thanh","thanh","thanh","632","thanh","thanh","thanh");
        Logins log=new Logins("thanh","thanh",datas,"thanh");

        dbManager.addAccount(log);

        Toast.makeText(this,"total :"+dbManager.getAccount(),Toast.LENGTH_SHORT).show();
        Log.d("thanhthanh","total:"+dbManager.getAccount().getError());*/


        DBLike dbLike = new DBLike(this);

        for (int i = 0; i <= 10; i++) {
            dbLike.addLike("123");
        }

        Toast.makeText(this, "toat", Toast.LENGTH_SHORT).show();
        Log.d("thanhthanh", "total :" + dbLike.getAllLike().size());

    }








}
