package com.mohammadsayed.architecture.network.volley;

import android.content.Context;

import com.mohammadsayed.architecture.core.BaseApiService;
import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.network.BaseRequestManager;

/**
 * Created by mohammad on 1/23/17.
 */

public abstract class VolleyService<Response> extends BaseApiService<Response> {

    public VolleyService(Context context, OnServiceSuccessListener<Response> onServiceSuccessListener, OnServiceErrorListener onServiceErrorListener) {
        super(context, onServiceSuccessListener, onServiceErrorListener);
    }

    @Override
    protected BaseRequestManager initializeRequestManager() {
        return VolleyRequestManager.getInstance(getContext().getApplicationContext());
    }

    public void cancelRequests() {
        if (getRequest() != null) {
            ((VolleyRequestManager) getRequestManager()).cancelRequest(getRequest().getOperationTag());
        }
    }
}
