package com.example.library;

import android.util.Log;

public class Utils {

    public void messageDisplay(String message){
        if(BuildConfig.DEBUG){
            Log.d("com.example.library",message);
        }
    }
}
