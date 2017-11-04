package com.mohammadsayed.celebrities.data.api.getPersonPhotos;

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

public class GetPersonPhotosService extends BaseVolleyService<GetPersonPhotosResponse> {

    private String TAG = GetPersonPhotosService.class.getSimpleName();
    private long mPersonId;

    public GetPersonPhotosService(Context context, long personId, OnServiceSuccessListener<GetPersonPhotosResponse> onServiceSuccessListener, OnServiceErrorListener onServiceErrorListener) {
        super(context, onServiceSuccessListener, onServiceErrorListener);
        this.mPersonId = personId;
    }

    @Override
    protected BaseRequest prepareRequest() {
        GetPersonPhotosRequest request = new GetPersonPhotosRequest(mPersonId);
        request.setOperationTag(TAG);
        if (request.getUrlParameters() == null) {
            request.setUrlParameters(new HashMap<String, String>());
        }
        request.getUrlParameters().putAll(getBaseUrlParameters());
        return request;
    }

    @Override
    protected BaseResponse prepareResponse() {
        GetPersonPhotosResponse getPersonPhotosResponse = new GetPersonPhotosResponse();
        getPersonPhotosResponse.setResponseClass(GetPersonPhotosResponse.class);
        return getPersonPhotosResponse;
    }

    @Override
    public void onSuccess(GetPersonPhotosResponse response) {
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
