package com.mohammadsayed.celebrities.persondetails;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohammadsayed.celebrities.AppUtility;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseInternetFragment;
import com.mohammadsayed.celebrities.data.Person;
import com.mohammadsayed.celebrities.data.PersonDetails;

import java.util.List;

public class PersonDetailsFragment extends BaseInternetFragment<PersonDetailsContract.Presenter>
        implements PersonDetailsContract.ViewCallback<PersonDetailsContract.Presenter> {


    private ImageView mIvProfilePicture;
    private TextView mTvName;
    private TextView mTvBiography;
    private TextView mTvPlaceOfBirth;
    private TextView mTvDateOfBirth;
    private TextView mTvDateOfDeathTitle;
    private TextView mTvDateOfDeath;

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
        mIvProfilePicture = view.findViewById(R.id.iv_person_details_profile_picture);
        mTvName = view.findViewById(R.id.tv_person_details_name);
        mTvBiography = view.findViewById(R.id.tv_person_details_bio);
        mTvPlaceOfBirth = view.findViewById(R.id.tv_person_details_place_of_birth);
        mTvDateOfBirth = view.findViewById(R.id.tv_person_details_birthday);
        mTvDateOfDeathTitle = view.findViewById(R.id.tv_person_details_death_day_title);
        mTvDateOfDeath = view.findViewById(R.id.tv_person_details_death_day);
        initializeRecyclerView(view);
    }

    private void initializeRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_person_details_images);
        int spanCount = 2;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        int displayWidth = AppUtility.getDisplayWidth(getActivity());
        //mPersonsAdapter = new PersonAdapter(getContext(), displayWidth, spanCount);
        //recyclerView.setAdapter(mPersonsAdapter);
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
