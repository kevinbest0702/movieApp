package com.dream.yzbb.movieapp.http;

import com.dream.yzbb.movieapp.utils.Constants;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kevinbest on 16/6/19.
 */
public class MovieApi {

    public interface MovieDetail{
        @GET(Constants.MovieDetailApiUrl)
        Call<ResponseBody> getMovieDetail(@Query("api_key")String apiKey);
    }
}
