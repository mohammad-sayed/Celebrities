package com.mohammadsayed.celebrities.explore;

import android.content.Context;

import com.mohammadsayed.architecture.core.Presenter;
import com.mohammadsayed.architecture.network.CoreError;

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

    //Repository Callbacks
    @Override
    public void onError(CoreError error) {
    }
}
