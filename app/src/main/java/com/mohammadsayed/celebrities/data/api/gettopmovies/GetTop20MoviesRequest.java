package com.mohammadsayed.celebrities.data.api.gettopmovies;

import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.bases.BaseGetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class GetTop20MoviesRequest extends BaseGetRequest {
    public GetTop20MoviesRequest(int page) {
        super(Constants.Network.GET_TOP_20_MOVIES_URI);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put(Constants.UrlParameters.Keys.PAGE, String.valueOf(page));
        setUrlParameters(urlParameters);
    }
}
