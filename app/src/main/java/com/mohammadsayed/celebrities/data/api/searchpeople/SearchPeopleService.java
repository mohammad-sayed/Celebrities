package com.mohammadsayed.celebrities.data.api.searchpeople;

import android.content.Context;

import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.network.BaseRequest;
import com.mohammadsayed.architecture.network.BaseResponse;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.celebrities.bases.BaseVolleyService;
import com.mohammadsayed.celebrities.data.api.getpopularpersons.GetPersonsResponse;

import java.util.HashMap;

/**
 * Created by mohammad on 1/16/17.
 */

public class SearchPeopleService extends BaseVolleyService<GetPersonsResponse> {

    private String TAG = SearchPeopleService.class.getSimpleName();
    private String mQuery;
    private int mPage;

    public SearchPeopleService(Context context, String query, int page, OnServiceSuccessListener<GetPersonsResponse> onServiceSuccessListener, OnServiceErrorListener onServiceErrorListener) {
        super(context, onServiceSuccessListener, onServiceErrorListener);
        this.mQuery = query;
        this.mPage = page;
    }

    @Override
    protected BaseRequest prepareRequest() {
        SearchPeopleRequest request = new SearchPeopleRequest(mQuery, mPage);
        request.setOperationTag(TAG);
        if (request.getUrlParameters() == null) {
            request.setUrlParameters(new HashMap<String, String>());
        }
        request.getUrlParameters().putAll(getBaseUrlParameters());
        return request;
    }

    @Override
    protected BaseResponse prepareResponse() {
        GetPersonsResponse searchPeopleResponse = new GetPersonsResponse();
        searchPeopleResponse.setResponseClass(GetPersonsResponse.class);
        return searchPeopleResponse;
    }

    @Override
    public void onSuccess(GetPersonsResponse response) {
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
