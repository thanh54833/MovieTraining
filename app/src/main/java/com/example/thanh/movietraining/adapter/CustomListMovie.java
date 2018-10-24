package com.example.thanh.movietraining.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.DownloadImageTask;
import com.example.library.Picassos;
import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.object.Movie;
import com.example.thanh.movietraining.view.ListMovieActivity;

import java.util.ArrayList;

public class CustomListMovie extends ArrayAdapter<Movie> {

    private Context mContext;
    private Movie dataModel;
    private ViewHolder viewHolder;
    public static boolean isAnimation;
    private int lastPosition = -1;


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
        if (isAnimation == true) {
            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            result.startAnimation(animation);
            lastPosition = position;
        }
        //load image download url connection ..
        //new DownloadImageTask(viewHolder.imageView).execute(dataModel.getUrl());
        //load image pocasso ...
        new Picassos(viewHolder.imageView,mContext,dataModel.getUrl());

        viewHolder.txtMovie.setText(dataModel.getMovie());
        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtView.setText(dataModel.getView());
        viewHolder.txtDescription.setText(dataModel.getDescription());
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
                ListMovieActivity.ActionLike(position);
            }
        });

       /* viewHolder.btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListMovieActivity.ActionWatch(position);
            }
        });*/

        return convertView;
    }

}