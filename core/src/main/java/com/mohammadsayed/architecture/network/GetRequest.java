package com.mohammadsayed.architecture.network;

import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohammad on 1/16/17.
 */

public class GetRequest extends BaseRequest {

    private Map<String, String> urlParameters;

    //Constructors----------------------------------------------------------------------------------
    protected GetRequest(String baseUrl) {
        super(baseUrl);
        setUrlParameters(new HashMap<String, String>());
    }

    //Methods --------------------------------------------------------------------------------------
    @Override
    public NetworkConstants.RequestMethod getRequestMethod() {
        return NetworkConstants.RequestMethod.GET;
    }


    //Getters & Setters ----------------------------------------------------------------------------
    @Override
    public String getUrl() {
        Uri.Builder uriBuilder = Uri.parse(super.getUrl()).buildUpon();
        for (Map.Entry<String, String> entry : urlParameters.entrySet()) {
            uriBuilder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return uriBuilder.build().toString();
    }

    public Map<String, String> getUrlParameters() {
        return urlParameters;
    }

    public void setUrlParameters(Map<String, String> urlParameters) {
        this.urlParameters = urlParameters;
    }


    public static class Builder extends BaseRequest.Builder<GetRequest, Builder> {

        public Builder(String baseUrl) {
            setRequest(new GetRequest(baseUrl));
        }

        public Builder setUrlParameters(Map<String, String> urlParameters) {
            getRequest().setUrlParameters(urlParameters);
            return this;
        }
    }
}
