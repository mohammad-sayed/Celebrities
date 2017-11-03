package com.mohammadsayed.architecture.core;

import android.view.View;

/**
 * Created by mohammad on 1/16/17.
 */

public interface BaseInternetViewCallback<P extends BasePresenter> extends BaseViewCallback<P> {
    void showSnackBar(final int messageStringId, final int duration);

    void showSnackBar(int messageStringId, int duration, int actionStringId, final View.OnClickListener onClickListener);

    void showSnackBar(String message, int duration, int actionStringId, final View.OnClickListener onClickListener);

    void showLoadingIndicator(boolean show);
}
