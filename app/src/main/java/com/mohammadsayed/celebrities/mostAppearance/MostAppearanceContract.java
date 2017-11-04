package com.mohammadsayed.celebrities.mostAppearance;

import com.mohammadsayed.architecture.core.BaseInternetViewCallback;
import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.architecture.core.BasePresenterCallback;
import com.mohammadsayed.architecture.core.BaseRepository;
import com.mohammadsayed.celebrities.data.Person;

import java.util.List;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class MostAppearanceContract {

    public interface ViewCallback<P extends BasePresenter> extends BaseInternetViewCallback<P> {
        void addPersonsToList(List<Person> persons);
    }

    public interface Presenter<V extends MostAppearanceContract.ViewCallback, R extends BaseRepository> extends BasePresenter<V, R> {
        void getMostAppearedPersons();
    }

    public interface PresenterCallback extends BasePresenterCallback {
        void onTopMoviesIdsRetrieved();

        void onMoviesCastsRetrieved();

        void onMostAppearedPersonsDetailsRetrieved(List<Person> personsList);
    }

    public interface Repository<P extends MostAppearanceContract.PresenterCallback> extends BaseRepository<P> {
        void getTopMoviesIds();

        void getMoviesCasts();

        void getMostAppearedPersonsDetails();
    }
}
