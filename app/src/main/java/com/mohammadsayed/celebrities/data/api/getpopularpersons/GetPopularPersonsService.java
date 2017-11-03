package com.mohammadsayed.celebrities.data.api.getpopularpersons;

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

public class GetPopularPersonsService extends BaseVolleyService<GetPopularPersonsResponse> {

    private String TAG = GetPopularPersonsService.class.getSimpleName();
    private int mPage;

    public GetPopularPersonsService(Context context, int page, OnServiceSuccessListener<GetPopularPersonsResponse> onServiceSuccessListener, OnServiceErrorListener onServiceErrorListener) {
        super(context, onServiceSuccessListener, onServiceErrorListener);
        this.mPage = page;
    }

    @Override
    protected BaseRequest prepareRequest() {
        GetPopularPersonsRequest getAppSettingsRequest = new GetPopularPersonsRequest(mPage);
        getAppSettingsRequest.setOperationTag(TAG);
        if (getAppSettingsRequest.getUrlParameters() == null) {
            getAppSettingsRequest.setUrlParameters(new HashMap<String, String>());
        }
        getAppSettingsRequest.getUrlParameters().putAll(getBaseUrlParameters());
        return getAppSettingsRequest;
    }

    @Override
    protected BaseResponse prepareResponse() {
        GetPopularPersonsResponse getPopularPersonsResponse = new GetPopularPersonsResponse();
        getPopularPersonsResponse.setResponseClass(GetPopularPersonsResponse.class);
        return getPopularPersonsResponse;
    }

    @Override
    public void onSuccess(GetPopularPersonsResponse response) {
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
