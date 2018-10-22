package com.example.thanh.movietraining.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.model.Movie;
import com.example.thanh.movietraining.view.ListMovieActivity;

import java.util.ArrayList;

import static com.example.thanh.movietraining.R.drawable.ic_like_ba;
import static com.example.thanh.movietraining.R.drawable.ic_like_orange_ba;

public class CustomListMovie extends ArrayAdapter<Movie> implements View.OnClickListener {

    private ArrayList<Movie> dataSet;
    private Context mContext;
    private Movie dataModel;
    private ViewHolder viewHolder;
    private boolean isAnimation;

    // View lookup cache
    private static class ViewHolder {
        ImageView imageView;
        TextView txtMovie;
        TextView txtName;
        TextView txtView;
        TextView txtDescription;
        Button btnlike;
        Button btnWatchMovie;
    }

    public CustomListMovie(ArrayList<Movie> data, Context context,boolean isAnimation) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext = context;
        this.isAnimation=isAnimation;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        Movie dataModel = (Movie) object;
    }

    private int lastPosition = -1;

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

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        if(isAnimation==true){
            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
            result.startAnimation(animation);
            lastPosition = position;
        }

        new DownloadImageTask(viewHolder.imageView).execute(dataModel.getUrl());
        viewHolder.txtMovie.setText(dataModel.getMovie());
        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtView.setText(dataModel.getView());
        viewHolder.txtDescription.setText(dataModel.getDescription());

        if (dataModel.isLike() == true) {
            viewHolder.btnlike.setBackgroundResource(R.drawable.ic_like_orange_ba);
        } else {
            viewHolder.btnlike.setBackgroundResource(R.drawable.ic_like_ba);
        }

        viewHolder.btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ListMovieActivity.ActionLike(position);

                Log.d("thanhthanh","result : true");

                if (dataModel.isLike() == false) {
                    //dataModel.setLike(true);
                    //Log.d("thanhthanh", "Click like :" + dataModel.isLike() + "position :" + position);
                    //viewHolder.btnlike.setBackgroundResource(ic_like_ba);
                } else {
                    //dataModel.setLike(false);
                    //Log.d("thanhthanh", "Click like :" + dataModel.isLike() + "position :" + position);

                    //viewHolder.btnlike.setBackgroundResource(ic_like_ba);
                    //notifyDataSetChanged();
                }
            }
        });

        viewHolder.btnWatchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("thanhthanh", "Click watch movie");
            }
        });

        return convertView;
    }


}