package com.dream.yzbb.movieapp.entity;

import java.util.List;

/**
 * Created by kevinbest on 16/6/21.
 */
public class TopRatedMovieInfo {
    public int page;
    public List<TopRatedMovie> results;
    public int total_results;
    public int total_pages;

    public int getPage() {
        return page;
    }

    public List<TopRatedMovie> getResults() {
        return results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }
}
