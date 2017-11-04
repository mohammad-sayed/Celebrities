package com.mohammadsayed.celebrities.data.api.gettopmovies;

import com.google.gson.annotations.SerializedName;
import com.mohammadsayed.architecture.network.BaseResponse;
import com.mohammadsayed.celebrities.data.Movie;

import java.util.List;

/**
 * Created by mohammad on 3/3/17.
 */

public class GetTop20MoviesResponse extends BaseResponse<GetTop20MoviesResponse> {

    @SerializedName("results")
    private List<Movie> moviesList;

    public List<Movie> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }
}
