package com.example.ekram.popularfilm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Movie;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.ivbaranov.mfb.MaterialFavoriteButton;
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

    private SQLiteDatabase fDB;
    private ImageButton favButton;

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

        favButton = (ImageButton) findViewById(R.id.btn_favorite);
        favButton.setBackgroundColor( Color.TRANSPARENT);

        int backgroundOpacity = 10 * 0x01000000;
        favButton.setBackgroundColor(backgroundOpacity + 0xff0000);

        ReleaseDate.setText(getString(R.string.releasdate_info)+ " \n" + CurrentMovie.getmReleaseDate());
        Rating.setText(getString(R.string.movie_rating)+ "\n " + CurrentMovie.getmRating());
        Title.setText(CurrentMovie.getmTitle());
        Overview.setText(CurrentMovie.getmOverview());


        Picasso.with(context).load(CurrentMovie.getmImagePath()).into(Poster);

    }
    
  /*  MaterialFavoriteButton materialFavoriteButtonNice =
            (MaterialFavoriteButton) findViewById(R.id.bt_favorite);
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
     materialFavoriteButtonNice.setOnFavoriteChangeListener(
             new MaterialFavoriteButton.OnFavoriteChangeListener(){
        @Override
        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite){
            if (favorite){
                SharedPreferences.Editor editor = getSharedPreferences("com.example.ekram.popularfilm.DetailActivity", MODE_PRIVATE).edit();
                editor.putBoolean("Favorite Added", true);
                editor.commit();
                saveFavorite();
                Snackbar.make(buttonView, "Added to Favorite",
                        Snackbar.LENGTH_SHORT).show();
            }else{
                int movie_id = getIntent().getExtras().getInt("id");
                favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
                favoriteDbHelper.deleteFavorite(movie_id);

                SharedPreferences.Editor editor = getSharedPreferences("com.example.ekram.popularfilm.DetailActivity", MODE_PRIVATE).edit();
                editor.putBoolean("Favorite Removed", true);
                editor.commit();
                Snackbar.make(buttonView, "Removed from Favorite",
                        Snackbar.LENGTH_SHORT).show();
            }

        }
    }
    ); */

}
