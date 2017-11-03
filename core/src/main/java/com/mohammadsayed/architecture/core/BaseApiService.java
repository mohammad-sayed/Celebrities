package com.mohammadsayed.architecture.core;

import android.content.Context;

import com.mohammadsayed.architecture.network.BaseRequest;
import com.mohammadsayed.architecture.network.BaseRequestManager;
import com.mohammadsayed.architecture.network.BaseResponse;
import com.mohammadsayed.architecture.network.OnRequestErrorListener;
import com.mohammadsayed.architecture.network.OnRequestSuccessListener;
import com.mohammadsayed.architecture.utils.ExceptionUtil;

/**
 * Created by mohammad on 1/16/17.
 */

public abstract class BaseApiService<Response> extends BaseService<Response> implements OnRequestSuccessListener<Response>, OnRequestErrorListener {

    private BaseRequest request;
    private BaseResponse response;
    private BaseRequestManager requestManager;

    public BaseApiService(Context context, OnServiceSuccessListener<Response> onServiceSuccessListener, OnServiceErrorListener onServiceErrorListener) {
        super(context, onServiceSuccessListener, onServiceErrorListener);
        setRequestManager(initializeRequestManager());
        if (getRequestManager() == null) {
            ExceptionUtil.throwRuntimeException("Request Manager Can't be NULL");
        }
    }

    //Methods---------------------------------------------------------------------------------------

    abstract protected BaseRequestManager initializeRequestManager();

    abstract protected BaseRequest prepareRequest();

    abstract protected BaseResponse prepareResponse();

    @Override
    public void start() {
        setRequest(prepareRequest());
        setResponse(prepareResponse());
        getRequestManager().sendRequest(getContext(), getRequest(), getResponse(), this, this);
    }

    //Getters & Setters ----------------------------------------------------------------------------

    public BaseRequestManager getRequestManager() {
        return requestManager;
    }

    protected BaseRequest getRequest() {
        return request;
    }

    protected void setRequest(BaseRequest request) {
        this.request = request;
    }

    protected BaseResponse getResponse() {
        return response;
    }

    protected void setResponse(BaseResponse response) {
        this.response = response;
    }

    protected void setRequestManager(BaseRequestManager requestManager) {
        this.requestManager = requestManager;
    }
}