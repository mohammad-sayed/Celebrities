package com.mohammadsayed.celebrities.persondetails;

import android.content.Context;

import com.mohammadsayed.architecture.core.OnServiceErrorListener;
import com.mohammadsayed.architecture.core.OnServiceSuccessListener;
import com.mohammadsayed.architecture.core.Repository;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.data.Person;
import com.mohammadsayed.celebrities.data.PersonDetails;
import com.mohammadsayed.celebrities.data.Photo;
import com.mohammadsayed.celebrities.data.api.getPersonPhotos.GetPersonPhotosResponse;
import com.mohammadsayed.celebrities.data.api.getPersonPhotos.GetPersonPhotosService;
import com.mohammadsayed.celebrities.data.api.getpersondetails.GetPersonDetailsResponse;
import com.mohammadsayed.celebrities.data.api.getpersondetails.GetPersonDetailsService;

import java.util.List;

/**
 * Created by mohammad on 1/25/17.
 */

public class PersonDetailsRepository extends Repository<PersonDetailsContract.PresenterCallback> implements PersonDetailsContract.Repository<PersonDetailsContract.PresenterCallback> {

    private Person mPerson;
    private PersonDetails mPersonDetails;
    private List<Photo> mPhotos;

    public PersonDetailsRepository(Context context) {
        super(context);
    }

    @Override
    public void savePersonDetails(Person person) {
        if (person == null) {
            return;
        }
        this.mPerson = person;
    }

    @Override
    public void getPersonDetails() {
        if (mPersonDetails != null) {
            getPresenterCallback().onPersonDetailsRetrieved(mPersonDetails);
            return;
        }
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
                mPersonDetails = response.convertToPersonalDetails();
                getPresenterCallback().onPersonDetailsRetrieved(mPersonDetails);
            }
        };

        GetPersonDetailsService service = new GetPersonDetailsService(getContext(), mPerson.getId(), onServiceSuccessListener, onServiceErrorListener);
        service.start();
    }

    @Override
    public void getPersonPhotos() {
        final OnServiceErrorListener onServiceErrorListener = new OnServiceErrorListener() {
            @Override
            public void onError(CoreError error) {
                error.setStatusCode(Constants.ErrorCodes.GET_PERSON_PHOTOS);
                getPresenterCallback().onError(error);
            }
        };

        final OnServiceSuccessListener<GetPersonPhotosResponse> onServiceSuccessListener = new OnServiceSuccessListener<GetPersonPhotosResponse>() {
            @Override
            public void onSuccess(GetPersonPhotosResponse response) {
                mPhotos = response.getPhotos();
                getPresenterCallback().onPersonPhotosRetrieved(mPhotos);
            }
        };

        GetPersonPhotosService service = new GetPersonPhotosService(getContext(), mPerson.getId(), onServiceSuccessListener, onServiceErrorListener);
        service.start();
    }
}
