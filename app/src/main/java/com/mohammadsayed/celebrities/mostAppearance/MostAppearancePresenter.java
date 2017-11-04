package com.mohammadsayed.celebrities.mostAppearance;

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

public class MostAppearancePresenter extends Presenter<MostAppearanceContract.ViewCallback, MostAppearanceContract.Repository>
        implements MostAppearanceContract.Presenter<MostAppearanceContract.ViewCallback, MostAppearanceContract.Repository>,
        MostAppearanceContract.PresenterCallback {

    public MostAppearancePresenter(Context context) {
        super(context);
    }

    //Repository Callbacks
    @Override
    public void onError(CoreError error) {
        switch (error.getStatusCode()) {
            case Constants.ErrorCodes.GET_TOP_20_MOVIES:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getTop20MoviesIds();
                    }
                });
                break;
            case Constants.ErrorCodes.GET_MOVIES_CREDITS:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMoviesCredits();
                    }
                });
                break;
            case Constants.ErrorCodes.GET_PERSONS_DETAILS:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMostAppearedPersonsList();
                    }
                });
                break;
            default:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_dismiss, null);
        }
        getViewCallback().showLoadingIndicator(false);

    }

    @Override
    protected MostAppearanceContract.Repository setUpRepository() {
        return new MostAppearanceRepository(getContext());
    }

    @Override
    public void getMostAppearedPersons() {
        getTop20MoviesIds();
    }

    private void getTop20MoviesIds() {
        getViewCallback().showLoadingIndicator(true);
        getRepository().getTopMoviesIds();
    }

    @Override
    public void onTopMoviesIdsRetrieved() {
        getViewCallback().showLoadingIndicator(false);
        getMoviesCredits();
    }

    private void getMoviesCredits() {
        getViewCallback().showLoadingIndicator(true);
        getRepository().getMoviesCredits();
    }

    @Override
    public void onMoviesCastsRetrieved() {
        getViewCallback().showLoadingIndicator(false);
        //getMostAppearedPersonsList();
    }

    private void getMostAppearedPersonsList() {
        getViewCallback().showLoadingIndicator(true);
        getRepository().getMostAppearedPersonsList();
    }

    @Override
    public void onMostAppearedPersonsRetrieved(List<Person> personsList) {
        getRepository().getMostAppearedPersonsList();
        getViewCallback().showLoadingIndicator(false);
    }

}
