package com.mohammadsayed.architecture.core;

/**
 * Created by mohammad on 1/16/17.
 */

public interface BasePresenter<V extends BaseViewCallback, R extends BaseRepository> {

    V getViewCallback();

    void setViewCallback(V viewCallback);

    R getRepository();

    void setRepository(R repository);

}
