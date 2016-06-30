package com.dream.yzbb.movieapp;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.yzbb.movieapp.databinding.FragmentTopRatedMovieBinding;
import com.dream.yzbb.movieapp.entity.TopRatedMovie;
import com.dream.yzbb.movieapp.entity.TopRatedMovieInfo;
import com.dream.yzbb.movieapp.entity.User;
import com.dream.yzbb.movieapp.http.MovieApi;
import com.dream.yzbb.movieapp.utils.Constants;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
    private RecyclerView mRecyclerView;
    private ArrayList<String> mMovieTitles;
    private ArrayList<String> mMovieImages;


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
        initView(view);
        initData();
    }

    public void assignValue() {
        Toast.makeText(getContext(), "Set user's name", Toast.LENGTH_SHORT).show();
        mUser.setName("Xianpzha");
    }

    private void initView(View view) {
        mButton = (Button) view.findViewById(R.id.action);
        mButton.setOnClickListener(this);
        FragmentTopRatedMovieBinding fragmentTopRatedMovieBinding = DataBindingUtil.bind(mRootView);
        fragmentTopRatedMovieBinding.setUser(mUser);
//        mImageView = (ImageView) view.findViewById(R.id.image);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setAdapter(new RecyclerViewAdapter(getContext()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action:
//                assignValue();
//                getMovieDetail();
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

    public class RecyclerViewAdapter extends RecyclerView.Adapter<MovieDetailHolder> {

        private LayoutInflater mLayoutInflater;
        private final Context mContext;

        @Override
        public MovieDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MovieDetailHolder(mLayoutInflater.inflate(R.layout.item_movie_detail, parent, false));
        }

        @Override
        public void onBindViewHolder(MovieDetailHolder holder, int position) {
            holder.mMovieTitle.setText(mMovieTitles.get(position));
            Picasso.with(mContext).load(Constants.MovieImageBaseUrl+mMovieImages.get(position)).into(holder.mMovieImage);
        }

        @Override
        public int getItemCount() {
            return mMovieImages.size();
        }

        public RecyclerViewAdapter(Context mContext) {
            this.mContext = mContext;
            mLayoutInflater = LayoutInflater.from(mContext);
        }
    }

    public static class MovieDetailHolder extends RecyclerView.ViewHolder {
        private ImageView mMovieImage;
        private TextView mMovieTitle;

        public MovieDetailHolder(View itemView) {
            super(itemView);
            mMovieImage = (ImageView) itemView.findViewById(R.id.image);
            mMovieTitle = (TextView) itemView.findViewById(R.id.movie_name);
        }
    }

    private void initData(){
        mMovieTitles = new ArrayList<>();
        mMovieTitles.add("Whiplash");
        mMovieTitles.add("The Shawshank Redemption");
        mMovieTitles.add("Lo chiamavano Jeeg Robot");
        mMovieTitles.add("The Godfather");
        mMovieTitles.add("Interstellar");
        mMovieTitles.add("千と千尋の神隠し");
        mMovieImages = new ArrayList<>();
        mMovieImages.add("/lIv1QinFqz4dlp5U4lQ6HaiskOZ.jpg");
        mMovieImages.add("/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg");
        mMovieImages.add("/poC4JgAnZYt1aOuUpFrsPpPZTXm.jpg");
        mMovieImages.add("/d4KNaTrltq6bpkFS01pYtyXa09m.jpg");
        mMovieImages.add("/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg");
        mMovieImages.add("/ynXoOxmDHNQ4UAy0oU6avW71HVW.jpg");
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
