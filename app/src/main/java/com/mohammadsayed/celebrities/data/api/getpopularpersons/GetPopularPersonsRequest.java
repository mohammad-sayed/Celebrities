package com.mohammadsayed.celebrities.data.api.getpopularpersons;

import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.bases.BaseGetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class GetPopularPersonsRequest extends BaseGetRequest {
    public GetPopularPersonsRequest(int page) {
        super(Constants.Network.GET_POPULAR_PERSONS_URI);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put(Constants.UrlParameters.Keys.PAGE, String.valueOf(page));
        setUrlParameters(urlParameters);
    }
}
