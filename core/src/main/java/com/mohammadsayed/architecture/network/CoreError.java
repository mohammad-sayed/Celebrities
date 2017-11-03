package com.mohammadsayed.architecture.network;

/**
 * Created by mohammad on 1/22/17.
 */

public class CoreError {

    private int statusCode;
    private String message;

    public CoreError() {
    }

    public CoreError(int statusCode, String message) {
        setStatusCode(statusCode);
        setMessage(message);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
