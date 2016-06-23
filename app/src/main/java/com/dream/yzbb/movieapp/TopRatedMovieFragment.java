package com.dream.yzbb.movieapp;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dream.yzbb.movieapp.databinding.FragmentTopRatedMovieBinding;
import com.dream.yzbb.movieapp.entity.TopRatedMovie;
import com.dream.yzbb.movieapp.entity.TopRatedMovieInfo;
import com.dream.yzbb.movieapp.entity.User;
import com.dream.yzbb.movieapp.http.MovieApi;
import com.dream.yzbb.movieapp.utils.Constants;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedMovieFragment extends Fragment implements View.OnClickListener {
    private User mUser = new User();
    private View mRootView;
    private Button mButton;
    private ImageView mImageView;


    public TopRatedMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_top_rated_movie, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButton = (Button) view.findViewById(R.id.action);
        mButton.setOnClickListener(this);
        FragmentTopRatedMovieBinding fragmentTopRatedMovieBinding = DataBindingUtil.bind(mRootView);
        fragmentTopRatedMovieBinding.setUser(mUser);
        mImageView = (ImageView) view.findViewById(R.id.image);
    }

    public void assignValue() {
        Toast.makeText(getContext(), "Set user's name", Toast.LENGTH_SHORT).show();
        mUser.setName("Xianpzha");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action:
//                assignValue();
                getMovieDetail();
                break;
        }
    }

    private void getMovieDetail() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.MovieApiBaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        MovieApi.MovieDetail movieDetail = retrofit.create(MovieApi.MovieDetail.class);
        Call<TopRatedMovieInfo> responseBodyCall = movieDetail.getMovieDetail(Constants.MovieApiKey);
        responseBodyCall.enqueue(new Callback<TopRatedMovieInfo>() {
            @Override
            public void onResponse(Call<TopRatedMovieInfo> call, Response<TopRatedMovieInfo> response) {
                TopRatedMovie firstTopRatedMovie = response.body().getResults().get(0);
                Log.d(Constants.LOG_TAG, "total page is " + response.body().getResults().get(0).poster_path);
                Picasso.with(getContext()).load(Constants.MovieImageBaseUrl + firstTopRatedMovie.poster_path).into(mImageView);
            }

            @Override
            public void onFailure(Call<TopRatedMovieInfo> call, Throwable t) {
                Log.e(Constants.LOG_TAG, "request failure, failure reason is " + t);
            }
        });
    }
}
