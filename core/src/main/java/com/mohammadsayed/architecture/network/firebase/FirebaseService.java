package com.mohammadsayed.architecture.network.firebase;

import android.content.Context;

import com.google.gson.Gson;
import com.mohammadsayed.architecture.core.BaseService;
import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.network.CoreError;

/**
 * Created by mohammad on 1/23/17.
 */

public abstract class FirebaseService<Response> extends BaseService<Response> {

    private Gson mGson = new Gson();
    private Class mClass;

    public FirebaseService(Context context, OnServiceSuccessListener<Response> onServiceSuccessListener, OnServiceErrorListener onServiceErrorListener) {
        super(context, onServiceSuccessListener, onServiceErrorListener);
    }

    public Gson getGson() {
        return mGson;
    }

    @Override
    public void onError(CoreError error) {
        getOnServiceErrorListener().onError(error);
    }

    @Override
    public void onSuccess(Response response) {
        getOnServiceSuccessListener().onSuccess(response);
    }
}
