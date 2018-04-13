package com.example.ekram.popularfilm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static boolean sort_bypop=true;
    ArrayList<MovieDetail> movies;
    MovieAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_sort_popularity:
                if (sort_bypop==true) break;
                sort_bypop=true;
                new FetchMovies().execute(sort_bypop);
                break;
            case R.id.action_sort_rating:
                if (sort_bypop==false) break;
                sort_bypop=false;
                new FetchMovies().execute(sort_bypop);


        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView =  findViewById(R.id.RecyclerView);

        new FetchMovies().execute(sort_bypop);


        adapter = new MovieAdapter(movies, this);

        adapter.setOnItemClickListenerHere(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent newIntent = new Intent(MainActivity.this, DetailActivity.class);
                MovieDetail movie = movies.get(position);
                newIntent.putExtra("CurrentMovie", movie );
                startActivity(newIntent);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    public class FetchMovies extends AsyncTask<Boolean, Void, ArrayList<MovieDetail>> {

        @Override
        protected ArrayList<MovieDetail> doInBackground(Boolean... booleans) {
            ArrayList<MovieDetail> downloadingMovies = new ArrayList<>();

            boolean sort_bypop = booleans[0];

            try {
                downloadingMovies = new movie_url().getMovies(sort_bypop);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return downloadingMovies;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieDetail> downloaded_movies) {
            movies = downloaded_movies;
            adapter.newMovies(downloaded_movies);
        }
    }
}