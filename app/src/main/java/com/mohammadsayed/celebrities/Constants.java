package com.mohammadsayed.celebrities;

/**
 * Created by Mohammad Sayed on 11/3/2017.
 */

public interface Constants {

    interface Network {
        String BASE_URL = "https://api.themoviedb.org/3";
        String GET_POPULAR_PERSONS_URI = "/person/popular";
        String SEARCH_PEOPLE_URI = "/search/person";
    }

    interface Image {
        String BASE_IMAGES_URL = "http://image.tmdb.org/t/p/";
        String PROFILE_SIZE = "w185";
    }

    interface UrlParameters {
        interface Keys {
            String API_KEY = "api_key";
            String PAGE = "page";
            String QUERY = "query";
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
        int EXPLORE_PERSONS = 2;
        int EXPLORE_MORE_PERSONS = 3;
    }

    interface ResponseCodes {
        int SUCCESS = 200;
    }

}
