package com.example.ekram.popularfilm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ekram on 16/04/2018.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>{

    private ArrayList<MovieTrailer> mTrailers = new ArrayList<>();

    TrailerAdapter(ArrayList<MovieTrailer> trailers){
        mTrailers = trailers;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trailer_list, parent, false);

        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {

        holder.trailer = mTrailers.get(position);
        String imageUrl = holder.trailer.getmImageUrl().toString();
        holder.bind(imageUrl);
    }

    @Override
    public int getItemCount() {
        if(mTrailers == null){
            return 0;
        }else{
            return mTrailers.size();
        }
    }

    public void setTrailerList(ArrayList<MovieTrailer> list){
        this.mTrailers = list;
        notifyDataSetChanged();
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder {

        private ImageView trailerImage;
        private Context context;
        private MovieTrailer trailer;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            trailerImage = itemView.findViewById(R.id.trailer_item_thumb);
        }

        private void bind(String imageUrl){
            Picasso.with(context)
                    .load(imageUrl)
                    .into(trailerImage);
        }
    }
}
