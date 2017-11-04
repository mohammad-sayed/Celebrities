package com.mohammadsayed.celebrities.data.api.getmoviecast;

import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.bases.BaseGetRequest;

import java.util.Locale;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public class GetMovieCreditsRequest extends BaseGetRequest {
    public GetMovieCreditsRequest(long page) {
        super(String.format(Locale.ENGLISH, Constants.Network.GET_MOVIE_CREDITS_URI, page));
    }
}
