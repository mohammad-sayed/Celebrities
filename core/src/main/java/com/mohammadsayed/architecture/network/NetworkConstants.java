package com.mohammadsayed.architecture.network;

/**
 * Created by mohammad on 1/18/17.
 */

public interface NetworkConstants {

    enum RequestMethod {GET, POST, DELETE, PUT}

    interface VolleyErrorCode {
        int TIMEOUT = 1;
        int NO_INTERNET_CONNECTION = 2;
        int UNKNOWN = 100;
    }
}
