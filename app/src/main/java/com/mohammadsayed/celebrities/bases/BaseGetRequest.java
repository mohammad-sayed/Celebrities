package com.mohammadsayed.celebrities.bases;

import com.mohammadsayed.architecture.network.GetRequest;
import com.mohammadsayed.celebrities.Constants;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public abstract class BaseGetRequest extends GetRequest {
    public BaseGetRequest(String uri) {
        super(Constants.Network.BASE_URL + uri);
    }
}
