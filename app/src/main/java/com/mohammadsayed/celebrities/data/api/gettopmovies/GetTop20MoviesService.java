package com.mohammadsayed.celebrities.data.api.gettopmovies;

import android.content.Context;

import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.network.BaseRequest;
import com.mohammadsayed.architecture.network.BaseResponse;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.celebrities.bases.BaseVolleyService;

import java.util.HashMap;

/**
 * Created by mohammad on 1/16/17.
 */

public class GetTop20MoviesService extends BaseVolleyService<GetTop20MoviesResponse> {

    private String TAG = GetTop20MoviesService.class.getSimpleName();
    private int mPage;

    public GetTop20MoviesService(Context context, int page, OnServiceSuccessListener<GetTop20MoviesResponse> onServiceSuccessListener, OnServiceErrorListener onServiceErrorListener) {
        super(context, onServiceSuccessListener, onServiceErrorListener);
        this.mPage = page;
    }

    @Override
    protected BaseRequest prepareRequest() {
        GetTop20MoviesRequest getAppSettingsRequest = new GetTop20MoviesRequest(mPage);
        getAppSettingsRequest.setOperationTag(TAG);
        if (getAppSettingsRequest.getUrlParameters() == null) {
            getAppSettingsRequest.setUrlParameters(new HashMap<String, String>());
        }
        getAppSettingsRequest.getUrlParameters().putAll(getBaseUrlParameters());
        return getAppSettingsRequest;
    }

    @Override
    protected BaseResponse prepareResponse() {
        GetTop20MoviesResponse getTop20MoviesResponse = new GetTop20MoviesResponse();
        getTop20MoviesResponse.setResponseClass(GetTop20MoviesResponse.class);
        return getTop20MoviesResponse;
    }

    @Override
    public void onSuccess(GetTop20MoviesResponse response) {
        //Callback_Step2_RespondToRepository_OnSuccess
        if (getOnServiceSuccessListener() != null) {
            getResponse().setResponse(response);
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
