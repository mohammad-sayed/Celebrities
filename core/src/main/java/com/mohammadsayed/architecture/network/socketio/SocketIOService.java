package com.mohammadsayed.architecture.network.socketio;

import android.content.Context;

import com.mohammadsayed.architecture.core.BaseService;
import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.network.CoreError;

/**
 * Created by mohammad on 5/4/17.
 */

public class SocketIOService<Response> extends BaseService<Response> {

    public SocketIOService(Context context, OnServiceSuccessListener<Response> onServiceSuccessListener, OnServiceErrorListener onServiceErrorListener) {
        super(context, onServiceSuccessListener, onServiceErrorListener);
    }


    @Override
    public void onError(CoreError error) {

    }

    @Override
    public void onSuccess(Response response) {

    }

    @Override
    public void start() {

    }
}
