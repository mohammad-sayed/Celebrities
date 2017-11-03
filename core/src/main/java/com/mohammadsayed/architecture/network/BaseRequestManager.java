package com.mohammadsayed.architecture.network;

import android.content.Context;

/**
 * Created by mohammad on 1/22/17.
 */

public abstract class BaseRequestManager {

    abstract public <T extends BaseResponse> void sendRequest(Context context, BaseRequest request, BaseResponse<T> response,
                                                              OnRequestSuccessListener onRequestSuccessListener,
                                                              OnRequestErrorListener responseErrorListener);
}