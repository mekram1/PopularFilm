package com.example.ekram.popularfilm;

import java.net.URL;

/**
 * Created by ekram on 16/04/2018.
 */

public class MovieTrailer {
    private URL mTrailerUrl;
    private URL mImageUrl;

    MovieTrailer(URL trailerUrl, URL imageUrl){
        mTrailerUrl = trailerUrl;
        mImageUrl = imageUrl;
    }

    public URL getTrailerURL(){ return mTrailerUrl; }
    public URL getmImageUrl() { return mImageUrl; }
}
