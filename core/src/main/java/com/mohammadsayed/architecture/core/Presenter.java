package com.mohammadsayed.architecture.core;

import android.content.Context;

/**
 * Created by mohammad on 1/16/17.
 */

public abstract class Presenter<V extends BaseViewCallback, R extends BaseRepository> implements BasePresenter<V, R>, BasePresenterCallback {

    private Context mContext;
    private V mViewCallback;
    private R mRepository;

    public Presenter(Context context) {
        setContext(context);
        setRepository(setUpRepository());
        mRepository.setPresenterCallback(this);
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public V getViewCallback() {
        return mViewCallback;
    }

    @Override
    public void setViewCallback(V viewCallback) {
        this.mViewCallback = viewCallback;
    }

    @Override
    public R getRepository() {
        return mRepository;
    }

    @Override
    public void setRepository(R repository) {
        this.mRepository = repository;
    }

    abstract protected R setUpRepository();

}
