package com.mohammadsayed.celebrities.data.api.searchpeople;

import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.bases.BaseGetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class SearchPeopleRequest extends BaseGetRequest {
    public SearchPeopleRequest(String query, int page) {
        super(Constants.Network.SEARCH_PEOPLE_URI);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put(Constants.UrlParameters.Keys.QUERY, query);
        urlParameters.put(Constants.UrlParameters.Keys.PAGE, String.valueOf(page));
        setUrlParameters(urlParameters);
    }
}
