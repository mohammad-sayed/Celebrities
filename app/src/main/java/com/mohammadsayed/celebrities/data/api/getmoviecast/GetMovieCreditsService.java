package com.mohammadsayed.celebrities.data.api.getmoviecast;

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

public class GetMovieCreditsService extends BaseVolleyService<GetMovieCreditsResponse> {

    private String TAG = GetMovieCreditsService.class.getSimpleName();
    private long mMovieId;

    public GetMovieCreditsService(Context context, long movieId, OnServiceSuccessListener<GetMovieCreditsResponse> onServiceSuccessListener, OnServiceErrorListener onServiceErrorListener) {
        super(context, onServiceSuccessListener, onServiceErrorListener);
        this.mMovieId = movieId;
    }

    @Override
    protected BaseRequest prepareRequest() {
        GetMovieCreditsRequest request = new GetMovieCreditsRequest(mMovieId);
        request.setOperationTag(TAG);
        if (request.getUrlParameters() == null) {
            request.setUrlParameters(new HashMap<String, String>());
        }
        request.getUrlParameters().putAll(getBaseUrlParameters());
        return request;
    }

    @Override
    protected BaseResponse prepareResponse() {
        GetMovieCreditsResponse getMovieCreditsResponse = new GetMovieCreditsResponse();
        getMovieCreditsResponse.setResponseClass(GetMovieCreditsResponse.class);
        return getMovieCreditsResponse;
    }

    @Override
    public void onSuccess(GetMovieCreditsResponse response) {
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
