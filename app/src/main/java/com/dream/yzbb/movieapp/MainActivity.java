package com.dream.yzbb.movieapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.dream.yzbb.movieapp.entity.TopRatedMovieInfo;
import com.dream.yzbb.movieapp.entity.User;
import com.dream.yzbb.movieapp.http.MovieApi;
import com.dream.yzbb.movieapp.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView mTextView;
    private User mUser = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragmentView();
    }

//    public void assignValue(View view) {
////        Toast.makeText(this, "Set user's name", Toast.LENGTH_SHORT).show();
////        mUser.setName("Xianpzha");
//        getMovieDetail();
//    }

    private void getMovieDetail() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.MovieApiBaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        MovieApi.MovieDetail movieDetail = retrofit.create(MovieApi.MovieDetail.class);
        Call<TopRatedMovieInfo> responseBodyCall = movieDetail.getMovieDetail(Constants.MovieApiKey);
        responseBodyCall.enqueue(new Callback<TopRatedMovieInfo>() {
            @Override
            public void onResponse(Call<TopRatedMovieInfo> call, Response<TopRatedMovieInfo> response) {
                Log.d(Constants.LOG_TAG, "total page is " + response.body().getResults().get(0).poster_path);
            }

            @Override
            public void onFailure(Call<TopRatedMovieInfo> call, Throwable t) {
                Log.e(Constants.LOG_TAG, "request failure, failure reason is " + t);
            }
        });
    }

    private void initActivityView(){
//        ActivityMainBinding activityMainBinding = DataBindingUtil.bind(findViewById(R.id.root_view));
//        mButton = (Button) findViewById(R.id.action);
//        mTextView = (TextView) findViewById(R.id.content);
//        activityMainBinding.setUser(mUser);
    }

    private void initFragmentView(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content,new TopRatedMovieFragment());
        transaction.commitAllowingStateLoss();
    }
}
