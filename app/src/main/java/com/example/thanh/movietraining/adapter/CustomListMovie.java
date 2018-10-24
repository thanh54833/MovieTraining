package com.example.thanh.movietraining.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.DownloadImageTask;
import com.example.library.Picassos;
import com.example.library.Util;
import com.example.thanh.movietraining.BuildConfig;
import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.Sqlite.DBAcount;
import com.example.thanh.movietraining.Utils;
import com.example.thanh.movietraining.object.Movie;
import com.example.thanh.movietraining.view.ListMovieActivity;
import com.example.thanh.movietraining.view.LoginActivity;
import com.example.thanh.movietraining.view.WatchMovieActivity;

import java.util.ArrayList;

public class CustomListMovie extends ArrayAdapter<Movie> {

    private Context mContext;
    private Movie dataModel;
    private ViewHolder viewHolder;
    public static boolean isAnimation;
    private int lastPosition = -1;
    private ArrayList<Movie> data;


    private static class ViewHolder {
        ImageView imageView;
        TextView txtMovie;
        TextView txtName;
        TextView txtView;
        TextView txtDescription;
        Button btnlike;
        Button btnWatchMovie;
        TextView txtLike;
    }

    public CustomListMovie(ArrayList<Movie> data, Context context, boolean isAnimation) {
        super(context, R.layout.row_item, data);

        this.mContext = context;
        this.isAnimation = isAnimation;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        dataModel = getItem(position);
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            viewHolder.txtMovie = (TextView) convertView.findViewById(R.id.txt_movie);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txt_name);
            viewHolder.txtView = (TextView) convertView.findViewById(R.id.txt_view);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.txt_description);
            viewHolder.btnlike = (Button) convertView.findViewById(R.id.btn_like);
            viewHolder.btnWatchMovie = (Button) convertView.findViewById(R.id.btn_watch_movie);
            viewHolder.txtLike = (TextView) convertView.findViewById(R.id.txt_like);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        //isAnimation == true
        if (false) {
            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            result.startAnimation(animation);
            lastPosition = position;
        }
        //load image download url connection ..
        //new DownloadImageTask(viewHolder.imageView).execute(dataModel.getUrl());
        //load image pocasso ...
        new Picassos(viewHolder.imageView, mContext, dataModel.getUrl());

        viewHolder.txtMovie.setText(dataModel.getMovie());
        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtView.setText(dataModel.getView());


        String decription = dataModel.getDescription();

        if (decription.length() > 200) {
            decription = decription.substring(0, 200) + "...";
        }

        viewHolder.txtDescription.setText(decription);
        if (dataModel.isLike() == true) {
            viewHolder.btnlike.setBackgroundResource(R.drawable.ic_like_orange_ba);
            viewHolder.txtLike.setText("Đã thích");
        } else {
            viewHolder.btnlike.setBackgroundResource(R.drawable.ic_like_ba);
            viewHolder.txtLike.setText("Thích");
        }
        viewHolder.btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBAcount dbManager = new DBAcount(getContext());
                if (dbManager.getAccount() != null && !dbManager.getAccount().data.getEmail().equals("")) {
                    new ListMovieActivity().ActionLike(position);
                }
            }
        });

        //final DBAcount dbManager = new DBAcount(mContext);
        viewHolder.btnWatchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WatchMovieActivity.ActionWatch(position, data);

                DBAcount dbManager = new DBAcount(getContext());
                if (dbManager.getAccount() != null && !dbManager.getAccount().data.getEmail().equals("")) {

                    Utils.messageDisplay("result gmail :" + dbManager.getAccount().data.getEmail());
                    Intent i = new Intent(mContext, WatchMovieActivity.class);
                    mContext.startActivity(i);
                } else {
                    Intent i = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(i);
                }


                //Utils.messageDisplay("Account :"+dbManager.getAccount().data.getEmail());

            }
        });

        return convertView;
    }

}