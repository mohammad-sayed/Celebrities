package com.mohammadsayed.celebrities.persondetails;

import android.content.Context;

import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.core.Repository;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.data.Person;
import com.mohammadsayed.celebrities.data.api.getpersondetails.GetPersonDetailsResponse;
import com.mohammadsayed.celebrities.data.api.getpersondetails.GetPersonDetailsService;

/**
 * Created by mohammad on 1/25/17.
 */

public class PersonDetailsRepository extends Repository<PersonDetailsContract.PresenterCallback> implements PersonDetailsContract.Repository<PersonDetailsContract.PresenterCallback> {

    private Person mPerson;

    public PersonDetailsRepository(Context context) {
        super(context);
    }

    @Override
    public void savePersonDetails(Person person) {
        this.mPerson = person;
    }

    @Override
    public void getPersonDetails() {
        final OnServiceErrorListener onServiceErrorListener = new OnServiceErrorListener() {
            @Override
            public void onError(CoreError error) {
                error.setStatusCode(Constants.ErrorCodes.GET_PERSON_DETAILS);
                getPresenterCallback().onError(error);
            }
        };

        final OnServiceSuccessListener<GetPersonDetailsResponse> onServiceSuccessListener = new OnServiceSuccessListener<GetPersonDetailsResponse>() {
            @Override
            public void onSuccess(GetPersonDetailsResponse response) {
                getPresenterCallback().onPersonDetailsRetrieved(response.convertToPersonalDetails());
            }
        };

        GetPersonDetailsService service = new GetPersonDetailsService(getContext(), mPerson.getId(), onServiceSuccessListener, onServiceErrorListener);
        service.start();
    }

    @Override
    public void getPersonImages() {

    }
}
