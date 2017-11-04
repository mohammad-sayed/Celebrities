package com.mohammadsayed.celebrities.persondetails;

import android.view.View;

import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseInternetFragment;
import com.mohammadsayed.celebrities.data.Person;
import com.mohammadsayed.celebrities.data.PersonDetails;

import java.util.List;

public class PersonDetailsFragment extends BaseInternetFragment<PersonDetailsContract.Presenter>
        implements PersonDetailsContract.ViewCallback<PersonDetailsContract.Presenter> {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_person_details;
    }

    @Override
    public String getFragmentTag() {
        return getClass().getSimpleName();
    }

    @Override
    public PersonDetailsContract.Presenter setUpPresenter() {
        return new PersonDetailsPresenter(getContext());
    }

    @Override
    protected void initializeViewsAndData(View view) {
        super.initializeViewsAndData(view);
    }

    public void setPerson(Person person) {
        getPresenter().getPersonDetails(person);
    }

    @Override
    public void displayPersonData(Person person) {

    }

    @Override
    public void displayPersonDetails(PersonDetails personDetails) {

    }

    @Override
    public void displayPersonImages(List<String> imagesPathList) {

    }
}
