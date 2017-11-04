package com.mohammadsayed.celebrities.data.api.getpersondetails;

import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.bases.BaseGetRequest;

import java.util.Locale;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class GetPersonDetailsRequest extends BaseGetRequest {
    public GetPersonDetailsRequest(long personId) {
        super(String.format(Locale.ENGLISH, Constants.Network.GET_PERSON_DETAILS_URI, personId));
    }
}
