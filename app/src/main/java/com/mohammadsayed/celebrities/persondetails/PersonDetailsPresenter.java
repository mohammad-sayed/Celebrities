package com.mohammadsayed.celebrities.persondetails;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.mohammadsayed.architecture.core.Presenter;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.data.Person;
import com.mohammadsayed.celebrities.data.PersonDetails;

import java.util.List;

/**
 * Created by mohammad on 1/15/17.
 */

public class PersonDetailsPresenter extends Presenter<PersonDetailsContract.ViewCallback, PersonDetailsContract.Repository>
        implements PersonDetailsContract.Presenter<PersonDetailsContract.ViewCallback, PersonDetailsContract.Repository>,
        PersonDetailsContract.PresenterCallback {

    public PersonDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected PersonDetailsContract.Repository setUpRepository() {
        return new PersonDetailsRepository(getContext());
    }

    //Repository Callbacks
    @Override
    public void onError(CoreError error) {
        switch (error.getStatusCode()) {
            case Constants.ErrorCodes.GET_PERSON_DETAILS:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPersonDetails();
                    }
                });
                break;
            default:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_dismiss, null);
        }
        getViewCallback().showLoadingIndicator(false);
    }

    @Override
    public void savePresonDetails(Person person) {
        getRepository().savePersonDetails(person);
        getViewCallback().displayPersonData(person);
    }

    @Override
    public void getPersonDetails() {
        getViewCallback().showLoadingIndicator(true);
        getRepository().getPersonDetails();
    }

    @Override
    public void onPersonDetailsRetrieved(PersonDetails personDetails) {
        getViewCallback().displayPersonDetails(personDetails);
        getViewCallback().showLoadingIndicator(false);
        getPersonImages();
    }

    private void getPersonImages() {
        getViewCallback().showLoadingIndicator(true);
        getRepository().getPersonImages();
    }

    @Override
    public void onPersonImagesRetrieved(List<String> imagesPathList) {
        getViewCallback().displayPersonImages(imagesPathList);
        getViewCallback().showLoadingIndicator(false);
    }
}
