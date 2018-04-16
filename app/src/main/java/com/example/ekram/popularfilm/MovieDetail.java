package com.example.ekram.popularfilm;

import java.io.Serializable;


/**
 * Created by ekram on 14/03/2018.
 */

public class MovieDetail implements Serializable {

    private String mTitle;
    private String mOverview;
    private String mRating;
    private String mReleaseDate;
    private String mImagePath;
    private String mMovieId;

    public MovieDetail(String Title, String Overview, String Rating, String ReleaseDate, String Image_Path, String movieId) {
        mTitle = Title;
        mOverview = Overview;
        mRating = Rating;
        mReleaseDate = ReleaseDate;
        mImagePath = Image_Path;
        mMovieId = movieId;
    }

    public MovieDetail() {

    }

    public String getmTitle() {
        return mTitle;
    }
    public String getmOverview() {
        return mOverview;
    }
    public String getmRating() {
        return mRating;
    }
    public String getmReleaseDate() {
        return mReleaseDate;
    }
    public String getmMovieId(){ return mMovieId; }

    public String getmImagePath() {
        return "http://image.tmdb.org/t/p/"+"w185"+mImagePath;
    }

}