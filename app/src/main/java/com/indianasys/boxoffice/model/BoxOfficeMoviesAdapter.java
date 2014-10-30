package com.indianasys.boxoffice.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.indianasys.boxoffice.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jide on 30/10/14.
 */
public class BoxOfficeMoviesAdapter extends ArrayAdapter<BoxOfficeMovie> {

    public BoxOfficeMoviesAdapter(Context context, ArrayList<BoxOfficeMovie> aMovies) {
        super(context, 0, aMovies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        BoxOfficeMovie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.card_picture, null);
        }

        // Lookup view for data population
        TextView tvTitle = (TextView) convertView.findViewById(R.id.title);
       /* TextView tvCriticsScore = (TextView) convertView.findViewById(R.id.tvCriticsScore);
        TextView tvCast = (TextView) convertView.findViewById(R.id.tvCast);*/
        ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.imageView1);
        TextView desc = (TextView) convertView.findViewById(R.id.description);
        // Populate the data into the template view using the data object
        tvTitle.setText(movie.getTitle());
        desc.setText(movie.getSynopsis().substring(0, 80)+ "..."  + "\n\n" + movie.getCastList());
        Picasso.with(getContext()).load(movie.getPosterUrl()).into(ivPosterImage);
       // tvCriticsScore.setText("Score: " + movie.getCriticsScore() + "%");


        // Return the completed view to render on screen
        return convertView;
    }


}
