package com.mohammadsayed.architecture.utils;

/**
 * Created by mohammad on 1/16/17.
 */

public interface Constants {

    String SUCCESSFUL = "successful";
    String FAILURE = "failure";

    String URL_BASE = "http://core-testing.getsandbox.com";//"http://212.100.202.153/NadecWS/nadekwebservice/Service.svc/Rest";
    String URI_GET_NEWS_LIST = "/GetNewsList";//"/getDDBCST_Customer_Master?serviceKey=nadekApp2016";

    boolean ENABLE = true;

    String DATE_SEPARATOR = "\\";

    interface ErrorCodes {
        int NO_INTERNET_CONNECTION = 1;
        int NO_SOCKET_CONNECTION = 2;
    }

    interface SharedPreferences {
        String LANGUAGE_TAG = "language";
    }

}
