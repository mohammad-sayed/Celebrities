package com.mohammadsayed.celebrities;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public interface Constants {

    interface Network {
        String BASE_URL = "https://api.themoviedb.org/3";
        String GET_POPULAR_PERSONS_URI = "/person/popular";
    }

    interface UrlParameters {
        interface Keys {
            String API_KEY = "api_key";
            String PAGE = "page";
        }

        interface Values {
            String API_KEY = "09793776922f7cfa7eb40d71dd697dd8";
        }
    }

    interface Gender {
        int FEMALE = 1;
        int MALE = 2;
    }

    interface ErrorCodes {
        int GET_POPULAR_PEOPLE = 1;
    }

    interface ResponseCodes {
        int SUCCESS = 200;
    }

}
