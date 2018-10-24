package com.example.thanh.movietraining.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.Utils;
import com.example.thanh.movietraining.fileconfig.FileConfig;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Example extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;

    YouTubePlayer.OnInitializedListener onInitializedListener;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        String s="https://www.youtube.com/watch?v=dCKm94RO4_M";
        String result=s.substring(s.indexOf("=")+1,s.length());

        Toast.makeText(getApplicationContext(),"result :"+result,Toast.LENGTH_SHORT).show();
        Utils.messageDisplay("result :"+result);
    }
}