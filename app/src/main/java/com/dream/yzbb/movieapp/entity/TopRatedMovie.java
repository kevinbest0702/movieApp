package com.dream.yzbb.movieapp.entity;

import java.util.List;

/**
 * Created by kevinbest on 16/6/21.
 */
public class TopRatedMovie {
    public String poster_path;
    public boolean adult;
    public String overview;
    public String release_date;
    public List<Integer> genre_ids;
    public String id;
    public String original_title;
    public String original_language;
    public String title;
    public String backdrop_path;
    public float popularity;
    public int vote_count;
    public boolean video;
    public float vote_average;

    @Override
    public String toString() {
        return "The information of one top-rated movie is following: \n Poster path is " + poster_path + ". ";
    }

    public String getPoster_path() {
        return poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public float getPopularity() {
        return popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVote_average() {
        return vote_average;
    }
}
