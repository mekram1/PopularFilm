package com.example.ekram.popularfilm;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ekram on 14/03/2018.
 */

public class movie_url {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
 //   private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    //api key here
    private static final String API = "";
    private static final String API_KEY = "api_key";
    private static final String SORT_BY = "sort_by";
    private static final String POPULARITY = "movie/popular";
    private static final String VOTE = "movie/top_rated";
    private static final String PAGE = "page";
    private static final String NUMBER = "1";
    final static String TRAILER_PATH = "videos";
    final static String REVIEW_PATH = "reviews";


    String JSONresponse;

    public ArrayList<MovieDetail> getMovies (boolean sort_bypop) throws IOException, JSONException {
        ArrayList<MovieDetail> movies = new ArrayList<>();

        String sorting_order;

        if (sort_bypop){
            sorting_order = POPULARITY;
        }
        else sorting_order = VOTE;

        Uri uri =  Uri.parse(BASE_URL).buildUpon()


                .appendEncodedPath(sorting_order)
                .appendQueryParameter(API_KEY, API)
                .appendQueryParameter(PAGE, NUMBER)
                .build();
    /**
     * Builds the URL used to query the top rated movies.
     * @return The URL to use to query the top rated movies.
     */

        URL url = new URL(uri.toString());


        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.connect();

        inputStream = urlConnection.getInputStream();
        StringBuilder builder = new StringBuilder();
        if (inputStream!=null){
            InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();

            while (line!=null){
                builder.append(line);
                line = bufferedReader.readLine();
            }
            JSONresponse = builder.toString();
        }
        if (!JSONresponse.isEmpty()){
            JSONObject object = new JSONObject(JSONresponse);
            JSONArray array = object.getJSONArray("results");
            int numberofMovies = array.length();
            for (int i = 0; i < numberofMovies; i++){
                JSONObject object1 = array.getJSONObject(i);
                String Title = object1.getString("title");
                String Overview = object1.getString("overview");
                String Rating = object1.getString("vote_average");
                String Date = object1.getString("release_date");
                String Image_Path = object1.getString("poster_path");
                String movieId = object1.getString("id");
                movies.add(new MovieDetail());
            }


        }
        return movies;
    }
    /* This method takes the path identifier "trailer" or "review" and movieId and returns the query URL.
                */

    public static URL buildUrlWithId (int pathIdentifier, String movieId){
        Uri.Builder builder = new Uri.Builder();
        switch (pathIdentifier){
            case 1:
                builder.scheme(BASE_URL)
                        .authority(BASE_URL)
                        .appendPath(movieId)
                        .appendPath(TRAILER_PATH)
                        .appendQueryParameter(API_KEY, API);
                break;
            case 2:
                builder.scheme(BASE_URL)
                        .authority(BASE_URL)
                        .appendPath(movieId)
                        .appendPath(REVIEW_PATH)
                        .appendQueryParameter(API_KEY, API);
                break;

        }

        String stringUrl = builder.build().toString();
        URL url = null;
        try {
            url = new URL(stringUrl);
            Log.v("movie_url", url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {

        // Checks if the Url is null
        if(url == null){
            return null;
        }
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();

            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}


