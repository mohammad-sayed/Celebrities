package com.mohammadsayed.celebrities.popular;

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

public class PopularPresenter extends Presenter<PopularContract.ViewCallback, PopularContract.Repository>
        implements PopularContract.Presenter<PopularContract.ViewCallback, PopularContract.Repository>,
        PopularContract.PresenterCallback {

    public PopularPresenter(Context context) {
        super(context);
    }

    @Override
    protected PopularContract.Repository setUpRepository() {
        return new PopularRepository(getContext());
    }

    //Repository Callbacks
    @Override
    public void onError(CoreError error) {
        switch (error.getStatusCode()) {
            case Constants.ErrorCodes.GET_POPULAR_PEOPLE:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPopularPeople();
                    }
                });
                break;
            default:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_dismiss, null);
        }
        getViewCallback().showLoadingIndicator(false);
    }

    @Override
    public void getPopularPeople() {
        getViewCallback().showLoadingIndicator(true);
        getRepository().getPopularPeople();
    }

    @Override
    public void onPopularPeopleRetrieved(List<Person> persons) {
        getViewCallback().addPopularPeopleToList(persons);
        getViewCallback().showLoadingIndicator(false);
    }
}
