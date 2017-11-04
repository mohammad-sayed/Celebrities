package com.mohammadsayed.celebrities.photo;

import android.content.Context;
import android.support.design.widget.Snackbar;

import com.mohammadsayed.architecture.core.Presenter;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.celebrities.R;

/**
 * Created by mohammad on 1/15/17.
 */

public class PhotoPresenter extends Presenter<PhotoContract.ViewCallback, PhotoContract.Repository>
        implements PhotoContract.Presenter<PhotoContract.ViewCallback, PhotoContract.Repository>,
        PhotoContract.PresenterCallback {

    public PhotoPresenter(Context context) {
        super(context);
    }

    @Override
    protected PhotoContract.Repository setUpRepository() {
        return new PhotoRepository(getContext());
    }

    //Repository Callbacks
    @Override
    public void onError(CoreError error) {
        switch (error.getStatusCode()) {
            default:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_dismiss, null);
        }
        getViewCallback().showLoadingIndicator(false);
    }
}
