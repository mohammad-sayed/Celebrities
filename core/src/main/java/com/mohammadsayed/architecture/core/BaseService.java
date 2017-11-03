package com.mohammadsayed.architecture.core;

import android.content.Context;

import com.mohammadsayed.architecture.network.OnRequestErrorListener;
import com.mohammadsayed.architecture.network.OnRequestSuccessListener;

/**
 * Created by mohammad on 1/16/17.
 */

public abstract class BaseService<Response> implements OnRequestSuccessListener<Response>, OnRequestErrorListener {

    private Context context;
    private OnServiceSuccessListener<Response> onServiceSuccessListener;
    private OnServiceErrorListener onServiceErrorListener;

    public BaseService(Context context, OnServiceSuccessListener<Response> onServiceSuccessListener, OnServiceErrorListener onServiceErrorListener) {
        setContext(context);
        setOnServiceSuccessListener(onServiceSuccessListener);
        setOnServiceErrorListener(onServiceErrorListener);
    }

    //Methods---------------------------------------------------------------------------------------
    abstract public void start();

    //Getters & Setters ----------------------------------------------------------------------------
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected OnServiceSuccessListener<Response> getOnServiceSuccessListener() {
        return onServiceSuccessListener;
    }

    protected void setOnServiceSuccessListener(OnServiceSuccessListener<Response> onServiceSuccessListener) {
        this.onServiceSuccessListener = onServiceSuccessListener;
    }

    protected OnServiceErrorListener getOnServiceErrorListener() {
        return onServiceErrorListener;
    }

    protected void setOnServiceErrorListener(OnServiceErrorListener onServiceErrorListener) {
        this.onServiceErrorListener = onServiceErrorListener;
    }
}