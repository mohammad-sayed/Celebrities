package com.mohammadsayed.architecture.services;

import android.content.Context;

import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.data.News;
import com.mohammadsayed.architecture.network.BaseRequest;
import com.mohammadsayed.architecture.network.BaseResponse;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.architecture.network.GetRequest;
import com.mohammadsayed.architecture.network.volley.VolleyService;
import com.mohammadsayed.architecture.utils.Constants;

/**
 * Created by mohammad on 1/16/17.
 */

public class GetNewsListService extends VolleyService<News[]> {

    private String TAG = GetNewsListService.class.getSimpleName();

    public GetNewsListService(Context context, OnServiceSuccessListener<News[]> onServiceSuccessListener, OnServiceErrorListener onServiceErrorListener) {
        super(context, onServiceSuccessListener, onServiceErrorListener);
    }

    @Override
    protected BaseRequest prepareRequest() {
        return new GetRequest.Builder(Constants.URL_BASE)
                .setUri(Constants.URI_GET_NEWS_LIST)
                .setOperationTag(TAG)
                .build();
    }

    @Override
    protected BaseResponse prepareResponse() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseClass(News[].class);
        return baseResponse;
    }

    @Override
    public void onSuccess(News[] response) {
        //Callback_Step2_RespondToRepository_OnSuccess
        if (getOnServiceSuccessListener() != null) {
            //getResponse().setResponse(response);
            getOnServiceSuccessListener().onSuccess(response);
        }
    }

    @Override
    public void onError(CoreError error) {
        //Callback_Step2_RespondToRepository_OnFailure
        if (getOnServiceErrorListener() != null) {
            getOnServiceErrorListener().onError(error);
        }
    }
}
