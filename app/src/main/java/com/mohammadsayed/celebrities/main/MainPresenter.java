package com.mohammadsayed.celebrities.main;

import android.content.Context;

import com.mohammadsayed.architecture.core.Presenter;
import com.mohammadsayed.architecture.network.CoreError;

/**
 * Created by mohammad on 1/15/17.
 */

public class MainPresenter extends Presenter<MainContract.ViewCallback, MainContract.Repository>
        implements MainContract.Presenter<MainContract.ViewCallback, MainContract.Repository>,
        MainContract.PresenterCallback {

    public MainPresenter(Context context) {
        super(context);
    }

    @Override
    protected MainContract.Repository setUpRepository() {
        return new MainRepository(getContext());
    }

    //Repository Callbacks
    @Override
    public void onError(CoreError error) {
    }
}
