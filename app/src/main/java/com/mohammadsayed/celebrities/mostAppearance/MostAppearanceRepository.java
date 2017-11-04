package com.mohammadsayed.celebrities.mostAppearance;

import android.content.Context;

import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.core.Repository;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.data.Movie;
import com.mohammadsayed.celebrities.data.api.gettopmovies.GetTop20MoviesResponse;
import com.mohammadsayed.celebrities.data.api.gettopmovies.GetTop20MoviesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mohammad on 1/25/17.
 */

public class MostAppearanceRepository extends Repository<MostAppearanceContract.PresenterCallback> implements MostAppearanceContract.Repository<MostAppearanceContract.PresenterCallback> {

    private List<Movie> mTopMovies = new ArrayList<>();
    private Map<Long, Integer> mPersonAppearance = new HashMap<>();

    public MostAppearanceRepository(Context context) {
        super(context);
    }

    @Override
    public void getTopMoviesIds() {
        final OnServiceErrorListener onServiceErrorListener = new OnServiceErrorListener() {
            @Override
            public void onError(CoreError error) {
                error.setStatusCode(Constants.ErrorCodes.GET_TOP_20_MOVIES);
                getPresenterCallback().onError(error);
            }
        };

        final OnServiceSuccessListener<GetTop20MoviesResponse> onServiceSuccessListener = new OnServiceSuccessListener<GetTop20MoviesResponse>() {
            @Override
            public void onSuccess(GetTop20MoviesResponse response) {
                mTopMovies = response.getMoviesList();
                getPresenterCallback().onTopMoviesIdsRetrieved();
            }
        };

        GetTop20MoviesService service = new GetTop20MoviesService(getContext(), 1, onServiceSuccessListener, onServiceErrorListener);
        service.start();
    }

    @Override
    public void getMoviesCasts() {

    }

    @Override
    public void getMostAppearedPersonsDetails() {

    }
}
