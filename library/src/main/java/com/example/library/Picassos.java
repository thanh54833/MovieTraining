package com.example.library;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Picassos {
    public Picassos(ImageView imageView, Context context,String url) {
        Picasso.with(context).load(url).error(R.drawable.ic_launcher_background).into(imageView);
    }
}
