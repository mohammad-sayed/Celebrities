package com.mohammadsayed.celebrities.explore;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.mohammadsayed.architecture.core.Presenter;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.data.Person;

import java.util.List;

/**
 * Created by mohammad on 1/15/17.
 */

public class ExplorePresenter extends Presenter<ExploreContract.ViewCallback, ExploreContract.Repository>
        implements ExploreContract.Presenter<ExploreContract.ViewCallback, ExploreContract.Repository>,
        ExploreContract.PresenterCallback {

    public ExplorePresenter(Context context) {
        super(context);
    }

    @Override
    protected ExploreContract.Repository setUpRepository() {
        return new ExploreRepository(getContext());
    }

    @Override
    public void onError(CoreError error) {
        switch (error.getStatusCode()) {
            case Constants.ErrorCodes.EXPLORE_PERSONS:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPerson(getViewCallback().getQuery());
                    }
                });
                break;
            case Constants.ErrorCodes.EXPLORE_MORE_PERSONS:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadMore();
                    }
                });
                break;
            default:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_dismiss, null);
        }
        getViewCallback().showLoadingIndicator(false);
    }

    @Override
    public void getPerson(String query) {
        getRepository().cancelSearchPeopleRequest();
        if (query.length() == 0) {
            getViewCallback().clearPersonsList();
        } else if (query.length() > 0) {
            getViewCallback().showNoResultMessage(false);
            getViewCallback().showLoadingIndicator(true);
            getRepository().getPerson(query);
        }
    }

    @Override
    public void loadMore() {
        getViewCallback().showLoadingIndicator(true);
        getRepository().loadMore();
    }

    @Override
    public void onPersonsRetrieved(List<Person> persons) {
        getViewCallback().setPersonsInList(persons);
        getViewCallback().showLoadingIndicator(false);
        if (persons.isEmpty()) {
            getViewCallback().showNoResultMessage(true);
        }
    }

    @Override
    public void onLoadMorePersonsRetrieved(List<Person> persons) {
        getViewCallback().addMorePersonsToList(persons);
        int personsCount = getViewCallback().getPersonsCount();
        if (personsCount == 0) {
            getViewCallback().showNoResultMessage(true);
        }
        getViewCallback().showLoadingIndicator(false);

    }
}
