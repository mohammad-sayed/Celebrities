package com.mohammadsayed.celebrities.explore;

import com.mohammadsayed.architecture.core.BaseInternetViewCallback;
import com.mohammadsayed.architecture.core.BasePresenter;
import com.mohammadsayed.architecture.core.BasePresenterCallback;
import com.mohammadsayed.architecture.core.BaseRepository;
import com.mohammadsayed.celebrities.data.Person;

import java.util.List;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class ExploreContract {

    public interface ViewCallback<P extends BasePresenter> extends BaseInternetViewCallback<P> {
        void showNoResultMessage(boolean show);

        void setPersonsInList(List<Person> persons);

        void addMorePersonsToList(List<Person> persons);

        String getQuery();

        void clearPersonsList();

        int getPersonsCount();
    }

    public interface Presenter<V extends ViewCallback, R extends BaseRepository> extends BasePresenter<V, R> {
        void getPerson(String query);

        void loadMore();
    }

    public interface PresenterCallback extends BasePresenterCallback {
        void onPersonsRetrieved(List<Person> persons);

        void onLoadMorePersonsRetrieved(List<Person> persons);
    }

    public interface Repository<P extends PresenterCallback> extends BaseRepository<P> {
        void getPerson(String query);

        void loadMore();

        void cancelSearchPeopleRequest();
    }

}
