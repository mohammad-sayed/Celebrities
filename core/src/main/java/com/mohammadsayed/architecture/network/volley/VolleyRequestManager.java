package com.mohammadsayed.architecture.network.volley;

import android.content.Context;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.mohammadsayed.architecture.R;
import com.mohammadsayed.architecture.network.BaseRequest;
import com.mohammadsayed.architecture.network.BaseRequestManager;
import com.mohammadsayed.architecture.network.BaseResponse;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.architecture.network.NetworkConstants;
import com.mohammadsayed.architecture.network.OnRequestErrorListener;
import com.mohammadsayed.architecture.network.OnRequestSuccessListener;
import com.mohammadsayed.architecture.network.PostRequest;
import com.mohammadsayed.architecture.utils.CLog;

import java.io.UnsupportedEncodingException;

/**
 * Created by mohammad on 1/16/17.
 */

public class VolleyRequestManager extends BaseRequestManager {

    private static VolleyRequestManager mVolleyRequestManager;
    private RequestQueue mQueue;


    private VolleyRequestManager(Context applicationContext) {
        mQueue = Volley.newRequestQueue(applicationContext);
    }

    public static VolleyRequestManager getInstance(Context applicationContext) {
        if (mVolleyRequestManager == null) {
            synchronized (VolleyRequestManager.class) {
                if (mVolleyRequestManager == null) {
                    mVolleyRequestManager = new VolleyRequestManager(applicationContext);
                }
            }
        }
        return mVolleyRequestManager;
    }

    @Override
    public <T extends BaseResponse> void sendRequest(final Context context, final BaseRequest request, BaseResponse<T> response,
                                                     final OnRequestSuccessListener onRequestSuccessListener,
                                                     final OnRequestErrorListener responseErrorListener) {
        // Request a string response from the provided URL.
        //int requestMethod = getRequestMethod(request.getRequestMethod());

        Response.Listener<T> successListener = new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {

                if (onRequestSuccessListener != null) {
                    onRequestSuccessListener.onSuccess(response);
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CoreError coreError = new CoreError();
                if (error.networkResponse != null) {
                    coreError.setStatusCode(error.networkResponse.statusCode);
                    try {
                        String message = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                        coreError.setMessage(message);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else if (error instanceof TimeoutError) {
                    coreError.setStatusCode(NetworkConstants.VolleyErrorCode.TIMEOUT);
                    coreError.setMessage(context.getString(R.string.error_response_timeout));
                } else if (error instanceof NoConnectionError) {
                    coreError.setStatusCode(NetworkConstants.VolleyErrorCode.NO_INTERNET_CONNECTION);
                    coreError.setMessage(context.getString(R.string.error_no_internet_connection));
                } else {
                    coreError.setStatusCode(NetworkConstants.VolleyErrorCode.UNKNOWN);
                    coreError.setMessage(context.getString(R.string.error_unknown));
                }
                responseErrorListener.onError(coreError);
            }
        };

        GsonJsonRequest<T> gsonJsonRequest = null;
        switch (request.getRequestMethod()) {
            case GET:
                gsonJsonRequest = new GsonJsonRequest<>(Request.Method.GET, request.getUrl(),
                        response.getResponseClass(), request.getHeaders(), null,
                        successListener, errorListener);
                break;
            case POST:
                gsonJsonRequest = new GsonJsonRequest<>(Request.Method.POST, request.getUrl(),
                        response.getResponseClass(), request.getHeaders(), ((PostRequest) request).getBody(),
                        successListener, errorListener);
                break;
        }

        if (gsonJsonRequest != null) {
            gsonJsonRequest.setTag(request.getOperationTag());
            // Add the request to the RequestQueue.
            CLog.i("Test-Request", gsonJsonRequest.toString());
            mQueue.add(gsonJsonRequest);
        }
    }

    public void cancelRequest(String tag) {
        mQueue.cancelAll(tag);
    }

}
