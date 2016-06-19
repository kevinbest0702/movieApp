package com.dream.yzbb.movieapp;

import com.dream.yzbb.movieapp.databinding.ActivityMainBinding;
import com.dream.yzbb.movieapp.entity.User;
import com.dream.yzbb.movieapp.http.MovieApi;
import com.dream.yzbb.movieapp.utils.Constants;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
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
        ActivityMainBinding activityMainBinding = DataBindingUtil.bind(findViewById(R.id.root_view));
        mButton = (Button) findViewById(R.id.action);
        mTextView = (TextView) findViewById(R.id.content);
//        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        activityMainBinding.setUser(mUser);
    }

    public void assignValue(View view) {
//        Toast.makeText(this, "Set user's name", Toast.LENGTH_SHORT).show();
//        mUser.setName("Xianpzha");
        getMovieDetail();
    }

    private void getMovieDetail() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.MovieApiBaseUrl).build();
        MovieApi.MovieDetail movieDetail = retrofit.create(MovieApi.MovieDetail.class);
        Call<ResponseBody> responseBodyCall = movieDetail.getMovieDetail(Constants.MovieApiKey);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(Constants.LOG_TAG,call.request().url().toString());
                try {
                    Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Http request failure: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
