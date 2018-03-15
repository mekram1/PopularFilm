package com.example.ekram.popularfilm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



/**
 * Created by ekram on 14/03/2018.
 */

public class DetailActivity extends AppCompatActivity{

    ImageView Poster;
    TextView Title;
    TextView Overview;
    TextView Rating;
    TextView ReleaseDate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        Context context = getApplicationContext();

        Intent newIntent = getIntent();
        MovieDetail CurrentMovie = (MovieDetail) newIntent.getSerializableExtra("CurrentMovie");

        Title = findViewById(R.id.movie_title);
        Poster = findViewById(R.id.iv_detail);
        Overview = findViewById(R.id.tv_Overview);
        Rating = findViewById(R.id.tv_Rating);
        ReleaseDate = findViewById(R.id.tv_date);


        ReleaseDate.setText(getString(R.string.releasdate_info)+ " " + CurrentMovie.getmReleaseDate());
        Rating.setText(getString(R.string.movie_rating)+ " " + CurrentMovie.getmRating());
        Title.setText(CurrentMovie.getmTitle());
        Overview.setText(CurrentMovie.getmOverview());


        Picasso.with(context).load(CurrentMovie.getmImagePath()).into(Poster);

    }
}
