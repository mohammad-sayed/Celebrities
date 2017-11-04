package com.mohammadsayed.celebrities.persondetails;

import android.content.Context;

import com.mohammadsayed.architecture.core.Repository;
import com.mohammadsayed.celebrities.data.Person;

/**
 * Created by mohammad on 1/25/17.
 */

public class PersonDetailsRepository extends Repository<PersonDetailsContract.PresenterCallback> implements PersonDetailsContract.Repository<PersonDetailsContract.PresenterCallback> {

    public PersonDetailsRepository(Context context) {
        super(context);
    }

    @Override
    public void getPersonDetails(Person person) {
        /*final OnServiceErrorListener onServiceErrorListener = new OnServiceErrorListener() {
            @Override
            public void onError(CoreError error) {
                error.setStatusCode(Constants.ErrorCodes.GET_POPULAR_PEOPLE);
                getPresenterCallback().onError(error);
            }
        };

        final OnServiceSuccessListener<GetPersonsResponse> onServiceSuccessListener = new OnServiceSuccessListener<GetPersonsResponse>() {
            @Override
            public void onSuccess(GetPersonsResponse response) {
                mPage = response.getPage();
                mTotalPages = response.getTotalPages();
                getPresenterCallback().onPopularPeopleRetrieved(response.getPersonsList());
            }
        };

        GetPopularPersonsService service = new GetPopularPersonsService(getContext(), mPage + 1, onServiceSuccessListener, onServiceErrorListener);
        service.start();*/
    }

    @Override
    public void getPersonImages() {

    }
}
