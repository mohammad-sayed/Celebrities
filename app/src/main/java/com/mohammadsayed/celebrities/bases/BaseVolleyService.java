package com.mohammadsayed.celebrities.bases;

import android.content.Context;

import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.network.BaseResponse;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.architecture.network.volley.VolleyService;
import com.mohammadsayed.celebrities.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohammad on 3/3/17.
 */

public abstract class BaseVolleyService<T extends BaseResponse> extends VolleyService<T> implements OnServiceSuccessListener<T> {

    public BaseVolleyService(final Context context, final OnServiceSuccessListener<T> onServiceSuccessListener, final OnServiceErrorListener onServiceErrorListener) {
        super(context, new OnServiceSuccessListener<T>() {
            @Override
            public void onSuccess(T data) {
                if (onServiceSuccessListener != null) {
                    onServiceSuccessListener.onSuccess(data);
                }
            }
        }, new

                OnServiceErrorListener() {
                    @Override
                    public void onError(CoreError error) {
                        error = ErrorManager.getInstance().handleApiError(context, error);
                        if (onServiceErrorListener != null) {
                            onServiceErrorListener.onError(error);
                        }
                    }
                });
    }

    protected Map<String, String> getBaseUrlParameters() {
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put(Constants.UrlParameters.Keys.API_KEY, Constants.UrlParameters.Values.API_KEY);
        return urlParameters;
    }
}
