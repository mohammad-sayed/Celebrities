package com.mohammadsayed.architecture.network;

/**
 * Created by mohammad on 1/16/17.
 */

public class PostRequest extends BaseRequest {

    private String body;

    protected PostRequest(String url) {
        super(url);
    }

    protected PostRequest(String url, String body) {
        super(url);
        setBody(body);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public NetworkConstants.RequestMethod getRequestMethod() {
        return NetworkConstants.RequestMethod.POST;
    }

    public static class Builder extends BaseRequest.Builder<PostRequest, Builder> {

        public Builder(String baseUrl) {
            setRequest(new PostRequest(baseUrl));
        }

        public PostRequest.Builder setBody(String body) {
            getRequest().setBody(body);
            return this;
        }
    }
}
