package com.example.ekram.popularfilm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by ekram on 14/03/2018.
 */

public class DetailActivity extends AppCompatActivity {

    ImageView Poster;
    TextView Title;
    TextView Overview;
    TextView Rating;
    TextView ReleaseDate;

    private SQLiteDatabase fDB;


    private boolean isEnable = false;
    private RecyclerView trailerRecyclerView;
    private TrailerAdapter trailerAdapter;
    private ArrayList<MovieTrailer> trailers = new ArrayList<>();
    private String movieId;
    private LinearLayoutManager trailerLayoutManager;
    private LinearLayoutManager reviewLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.movie_detail );

        Context context = getApplicationContext();

        Intent newIntent = getIntent();
        MovieDetail CurrentMovie = (MovieDetail) newIntent.getSerializableExtra( "CurrentMovie" );

        Title = findViewById( R.id.movie_title );
        Poster = findViewById( R.id.iv_detail );
        Overview = findViewById( R.id.tv_Overview );
        Rating = findViewById( R.id.tv_Rating );
        ReleaseDate = findViewById( R.id.tv_date );


        ReleaseDate.setText( getString( R.string.releasdate_info ) + " \n" + CurrentMovie.getmReleaseDate() );
        Rating.setText( getString( R.string.movie_rating ) + "\n " + CurrentMovie.getmRating() );
        Title.setText( CurrentMovie.getmTitle() );
        Overview.setText( CurrentMovie.getmOverview() );

        movieId = MovieDetail.getMovieId();
        Log.v("movieId", movieId);

        Picasso.with( context ).load( CurrentMovie.getmImagePath() ).into( Poster );


        new TrailerAsyncTask().execute( movieId );
        trailerRecyclerView = findViewById( R.id.rv_trailers );
        trailerAdapter = new TrailerAdapter( trailers );
        trailerLayoutManager =
                new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL, false );

        trailerRecyclerView.setLayoutManager( trailerLayoutManager );
        trailerRecyclerView.setHasFixedSize( true );
        trailerRecyclerView.setAdapter( trailerAdapter );

    }

    public class TrailerAsyncTask extends AsyncTask<String, Void, ArrayList<MovieTrailer>> {

        @Override
        protected ArrayList<MovieTrailer> doInBackground(String... strings) {
            String movieId = strings[0];
            ArrayList<MovieTrailer> urls = new ArrayList<>();
            try {
                String trailerJson = movie_url.getResponseFromHttpUrl( movie_url.buildUrlWithId( 1, movieId ) );
                urls = MovieTrailerJson.getDetailsFromTrailerJson( trailerJson );
                Log.v( "arraylist", urls.toString() );
                return urls;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieTrailer> movieTrailers) {
            super.onPostExecute( movieTrailers );
            trailers = movieTrailers;
        }
    }
}