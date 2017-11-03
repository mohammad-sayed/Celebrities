package com.mohammadsayed.architecture.core;

import android.content.Context;

/**
 * Created by mohammad on 1/16/17.
 */

public abstract class Repository<PCB extends BasePresenterCallback> implements BaseRepository<PCB> {

    private Context mContext;

    private PCB mPresenterCallback;

    public Repository(Context context) {
        setContext(context);
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    @Override
    public PCB getPresenterCallback() {
        return mPresenterCallback;
    }

    @Override
    public void setPresenterCallback(PCB presenterCallback) {
        this.mPresenterCallback = presenterCallback;
    }
}
