package com.example.ekram.popularfilm.data;

/**
 * Created by ekram on 12/04/2018.
 */

import android.net.Uri;
import android.provider.BaseColumns;

public class FavoriteContract {

    /* public static final class FavoriteEntry implements BaseColumns {

         public static final String TABLE_NAME = "favorite";
         public static final String COLUMN_TITLE = "title";
         public static final String COLUMN_USERRATING = "rating";
         public static final String COLUMN_IMAGE_PATH = "posterpath";
         public static final String COLUMN_OVERVIEW = "overview";
         public static final String COLUMN_RELEASEDATE = "releasedate";
     }
     */
    public static final String AUTHORITY = "com.example.ekram.popularfilm";
    public static final Uri BASE_URI = Uri.parse( "content://" + AUTHORITY );
    public static final String PATH_FAVORITES = "favorites";

    static public final class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_MOVIE_ID = "movieID";
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath( PATH_FAVORITES ).build();
    }
}