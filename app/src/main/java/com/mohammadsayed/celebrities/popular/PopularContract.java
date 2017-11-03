package com.mohammadsayed.celebrities.popular;

import com.mohammadsayed.architecture.core.BaseInternetViewCallback;
import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.architecture.core.BasePresenterCallback;
import com.mohammadsayed.architecture.core.BaseRepository;
import com.mohammadsayed.celebrities.data.Person;

import java.util.List;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class PopularContract {

    public interface ViewCallback<P extends BasePresenter> extends BaseInternetViewCallback<P> {
        void addPopularPeopleToList(List<Person> persons);
    }

    public interface Presenter<V extends ViewCallback, R extends BaseRepository> extends BasePresenter<V, R> {
        void getPopularPeople();
    }

    public interface PresenterCallback extends BasePresenterCallback {
        void onPopularPeopleRetrieved(List<Person> persons);
    }

    public interface Repository<P extends PresenterCallback> extends BaseRepository<P> {
        void getPopularPeople();
    }

}
