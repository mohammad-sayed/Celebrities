package com.mohammadsayed.architecture.core;

/**
 * Created by mohammad on 1/16/17.
 */

public interface BaseViewCallback<P extends BasePresenter> {

    P setUpPresenter();

    void setPresenter(P presenter);

    P getPresenter();

}
