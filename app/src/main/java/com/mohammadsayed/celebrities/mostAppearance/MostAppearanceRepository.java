package com.mohammadsayed.celebrities.mostAppearance;

import android.content.Context;

import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.core.Repository;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.data.Movie;
import com.mohammadsayed.celebrities.data.Person;
import com.mohammadsayed.celebrities.data.api.getmoviecast.GetMovieCreditsResponse;
import com.mohammadsayed.celebrities.data.api.getmoviecast.GetMovieCreditsService;
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
    private Map<Movie, List<Person>> mMoviesCreditsMap = new HashMap<>();
    private Map<Person, Integer> mPersonAppearance = new HashMap<Person, Integer>() {

    };
    private int mMovieIndex = 0;

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
    public void getMoviesCredits() {
        if (mTopMovies == null) {
            getPresenterCallback().onMoviesCastsRetrieved();
            return;
        }

        if (mMovieIndex == 0) {
            mMoviesCreditsMap.clear();
        }

        final OnServiceErrorListener onServiceErrorListener = new OnServiceErrorListener() {
            @Override
            public void onError(CoreError error) {
                error.setStatusCode(Constants.ErrorCodes.GET_MOVIES_CREDITS);
                getPresenterCallback().onError(error);
            }
        };

        final OnServiceSuccessListener<GetMovieCreditsResponse> onServiceSuccessListener = new OnServiceSuccessListener<GetMovieCreditsResponse>() {
            @Override
            public void onSuccess(GetMovieCreditsResponse response) {
                mMoviesCreditsMap.put(mTopMovies.get(mMovieIndex), response.getPersonList());
                mMovieIndex++;
                if (mMovieIndex < mTopMovies.size()) {
                    getMoviesCredits();
                } else {
                    mMovieIndex = 0;
                    getPresenterCallback().onMoviesCastsRetrieved();
                }
            }
        };
        long movieId = mTopMovies.get(mMovieIndex).getId();
        GetMovieCreditsService service = new GetMovieCreditsService(getContext(), movieId, onServiceSuccessListener, onServiceErrorListener);
        service.start();
    }

    @Override
    public void getMostAppearedPersonsList() {
        computePersonsAppearance();
        List<Person> mostAppearedPersonsList = getMostAppearedPersons();
        getPresenterCallback().onMostAppearedPersonsRetrieved(mostAppearedPersonsList);
        mPersonAppearance.clear();
    }

    private List<Person> getMostAppearedPersons() {
        List<Person> mostAppearedPersons = new ArrayList<>();
        for (Map.Entry<Person, Integer> entry : mPersonAppearance.entrySet()) {
            int numberOfAppearance = entry.getValue();
            if (numberOfAppearance > 1) {
                mostAppearedPersons.add(entry.getKey());
            }
        }
        return mostAppearedPersons;
    }

    private void computePersonsAppearance() {
        for (Map.Entry<Movie, List<Person>> entry : mMoviesCreditsMap.entrySet()) {
            List<Person> personsList = entry.getValue();
            if (personsList != null) {
                for (Person person : personsList) {
                    if (!mPersonAppearance.containsKey(person)) {
                        mPersonAppearance.put(person, 1);
                    } else {
                        int numberOfAppearance = mPersonAppearance.get(person);
                        numberOfAppearance++;
                        mPersonAppearance.put(person, numberOfAppearance);
                    }
                }
            }
        }
    }
}
