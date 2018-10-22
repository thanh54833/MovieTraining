package com.example.thanh.movietraining;

import android.os.Environment;

import java.io.File;

public class Utils {
    public static final File FILE_DATABASE= new File(Environment.getExternalStorageDirectory() +"/MovieTraining", "Buffer.txt");
}
