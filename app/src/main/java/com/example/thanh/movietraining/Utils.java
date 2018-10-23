package com.example.thanh.movietraining;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class Utils {
    public static final File FILE_DATABASE= new File(Environment.getExternalStorageDirectory() +"/MovieTraining", "Buffer.txt");
    public static String TAG="AppMovie";
    public static String key_token="dCuW7UQMbdvpcBDfzolAOSGFIcAec11a";
    public static void  messageDisplay(String message){
        if(BuildConfig.DEBUG){
            Log.i(TAG,message);
        }
    }
}
