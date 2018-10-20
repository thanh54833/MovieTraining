package com.example.thanh.movietraining.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.DownloadImageTask;
import com.example.thanh.movietraining.R;
import com.example.thanh.movietraining.model.Movie;

import java.util.ArrayList;

public class CustomListMovie extends ArrayAdapter<Movie> implements View.OnClickListener{

    private ArrayList<Movie> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView imageView;
        TextView txtMovie;
        TextView txtName;
        TextView txtView;
        TextView txtDescription;
    }

    public CustomListMovie(ArrayList<Movie> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Movie dataModel=(Movie) object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
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

            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;


        new DownloadImageTask(viewHolder.imageView).execute(dataModel.getUrl());

        viewHolder.txtMovie.setText(dataModel.getMovie());
        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtView.setText(dataModel.getView());
        viewHolder.txtDescription.setText(dataModel.getDescription());

        return convertView;
    }



}