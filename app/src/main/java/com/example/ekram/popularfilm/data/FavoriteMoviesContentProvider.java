package com.example.ekram.popularfilm.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by ekram on 13/04/2018.
 */

public class FavoriteMoviesContentProvider extends ContentProvider{
    public static final int FAVORITE_MOVIES = 100;
    public static final int FAVORITE_MOVIES_WITH_ID = 100;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher(){

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(FavoriteContract.AUTHORITY,FavoriteContract.PATH_FAVORITES,FAVORITE_MOVIES);
        uriMatcher.addURI(FavoriteContract.AUTHORITY,FavoriteContract.PATH_FAVORITES + "/#",FAVORITE_MOVIES);

        return uriMatcher;
    }


    private FavoriteDbHelper mDbHelper;

    @Override
    public boolean onCreate() {

        Context context = getContext();
        mDbHelper = new FavoriteDbHelper(context);
        return true;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {

        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        Uri returnURI;

        switch (match){
            case FAVORITE_MOVIES:
                long id = db.insert(FavoriteContract.FavoriteEntry.TABLE_NAME,null,values);
                if (id>0){
                    returnURI = ContentUris.withAppendedId(FavoriteContract.FavoriteEntry.CONTENT_URI,id);
                }else{
                    throw new android.database.SQLException("failed to insert row: " + uri);
                }

                break;

            default:
                throw new UnsupportedOperationException("unknown uri: " + uri);


        }

        getContext().getContentResolver().notifyChange(uri,null);
        return returnURI;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public String getType(@NonNull Uri uri) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

}
