package com.mohammadsayed.architecture.network;

/**
 * Created by mohammad on 1/16/17.
 */

public class BaseResponse<T extends BaseResponse> {

    private int code;

    private String message;

    private Class<T> responseClass = (Class<T>) getClass();

    private T response;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Class getResponseClass() {
        return responseClass;
    }

    public void setResponseClass(Class responseClass) {
        this.responseClass = responseClass;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
