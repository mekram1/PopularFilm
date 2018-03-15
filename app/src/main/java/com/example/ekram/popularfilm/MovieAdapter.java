package com.example.ekram.popularfilm;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;;

/**
 * Created by ekram on 14/03/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {



    private List<MovieDetail> movieList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public MovieAdapter (List<MovieDetail> movies, Context context){
        movieList = movies;
        mContext = context;
    }

    private Context getContext () {
        return mContext;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListenerHere (OnItemClickListener listenerHere){
        this.onItemClickListener = listenerHere;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View Movie = inflater.inflate(R.layout.movie, parent, false);
        ViewHolder viewHolder = new ViewHolder(Movie);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        MovieDetail movie = movieList.get(position);
        ImageView imageView = holder.imageView;
        Picasso.with(getContext()).load(movie.getmImagePath()).into(imageView);
    }

    @Override
    public int getItemCount() {
        if (movieList==null) return 0;
        else return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movieposter);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            onItemClickListener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    public void newMovies(ArrayList<MovieDetail> movies){
        movieList = movies;
        notifyDataSetChanged();
    }
}
