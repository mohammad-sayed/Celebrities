package com.mohammadsayed.architecture.network;

import android.net.Uri;

import com.mohammadsayed.architecture.utils.ExceptionUtil;
import com.mohammadsayed.architecture.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohammad on 1/16/17.
 */

public abstract class BaseRequest {

    private Map<String, String> headers;

    private String url;

    private ArrayList<String> pathParameters;

    private String operationTag;

    protected BaseRequest(String url) {
        setUrl(url);
        setHeaders(new HashMap<String, String>());
        setPathParameters(new ArrayList<String>());
    }


    //Methods --------------------------------------------------------------------------------------
    abstract public NetworkConstants.RequestMethod getRequestMethod();

    public void addHeader(String key, String value) {
        getHeaders().put(key, value);
    }

    public void addPathParameter(String parameter) {
        getPathParameters().add(parameter);
    }

    private String concatenatePathParameters() {
        Uri.Builder builder = Uri.parse(url).buildUpon();
        for (String string : pathParameters) {
            builder.appendPath(string);
        }
        return builder.build().toString();
    }

    //Getters and Setters --------------------------------------------------------------------------
    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getUrl() {
        return concatenatePathParameters();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUri(String uri) {
        this.url = getUrl() + uri;
    }

    public ArrayList<String> getPathParameters() {

        return pathParameters;
    }

    public void setPathParameters(ArrayList<String> pathParameters) {
        this.pathParameters = pathParameters;
    }

    public String getOperationTag() {
        return operationTag;
    }

    public void setOperationTag(String operationTag) {
        this.operationTag = operationTag;
    }

    abstract public static class Builder<R extends BaseRequest, B extends Builder> {

        private R request;

        public B setUrl(String url) {
            request.setUrl(url);
            return (B) this;
        }

        public B setUri(String uri) {
            request.setUri(uri);
            return (B) this;
        }

        public B setPathParameters(ArrayList<String> pathParameters) {
            request.setPathParameters(pathParameters);
            return (B) this;
        }

        public B setHeaders(Map<String, String> headers) {
            request.setHeaders(headers);
            return (B) this;
        }

        public B setOperationTag(String operationTag) {
            request.setOperationTag(operationTag);
            return (B) this;
        }

        public R build() {
            if (StringUtil.isEmpty(request.getUrl(), true)) {
                ExceptionUtil.throwRuntimeException("No URL inserted");
            }
            return request;
        }

        protected R getRequest() {
            return request;
        }

        protected void setRequest(R request) {
            this.request = request;
        }
    }
}
