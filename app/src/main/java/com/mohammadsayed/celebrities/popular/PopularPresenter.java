package com.mohammadsayed.celebrities.popular;

import android.content.Context;

import com.mohammadsayed.architecture.core.Presenter;
import com.mohammadsayed.architecture.network.CoreError;

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
    }
}
