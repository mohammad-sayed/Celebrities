package com.mohammadsayed.celebrities.persondetails;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohammadsayed.architecture.utils.StringUtil;
import com.mohammadsayed.celebrities.AppUtility;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseInternetFragment;
import com.mohammadsayed.celebrities.data.Person;
import com.mohammadsayed.celebrities.data.PersonDetails;
import com.mohammadsayed.celebrities.data.Photo;
import com.mohammadsayed.celebrities.photo.PhotoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PersonDetailsFragment extends BaseInternetFragment<PersonDetailsContract.Presenter>
        implements PersonDetailsContract.ViewCallback<PersonDetailsContract.Presenter>,
        PersonPhotosAdapter.OnPersonPhotoSelectedListener {


    private ImageView mIvProfilePicture;
    private TextView mTvName;
    private TextView mTvBiography;
    private TextView mTvPlaceOfBirth;
    private TextView mTvDateOfBirth;
    private TextView mTvDateOfDeathTitle;
    private TextView mTvDateOfDeath;
    private PersonPhotosAdapter mPersonPhotosAdapter;

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
        int spanCount = 3;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        int displayWidth = AppUtility.getDisplayWidth(getActivity());
        mPersonPhotosAdapter = new PersonPhotosAdapter(getContext(), displayWidth, spanCount, this);
        recyclerView.setAdapter(mPersonPhotosAdapter);
    }

    public void setPerson(Person person) {
        if (person == null) {
            return;
        }
        getPresenter().savePresonDetails(person);
        getPresenter().getPersonDetails();
    }

    @Override
    public void displayPersonData(Person person) {
        if (getContext() == null || person == null) {
            return;
        }
        if (!StringUtil.isEmpty(person.getProfilePicture(), true)) {
            String fullUrl = AppUtility.getFullUrl(Constants.Photo.PROFILE_SIZE, person.getProfilePicture());
            Picasso.with(getContext()).load(fullUrl).error(R.drawable.img_not_found).into(mIvProfilePicture);
        } else {
            Picasso.with(getContext()).load(R.drawable.img_not_found).into(mIvProfilePicture);
        }
        mTvName.setText(person.getName());
    }

    @Override
    public void displayPersonDetails(PersonDetails personDetails) {
        mTvBiography.setText(personDetails.getBiography());
        String placeOfBirth = personDetails.getPlaceOfBirth();
        if (StringUtil.isEmpty(placeOfBirth, true)) {
            placeOfBirth = getString(R.string.personal_details_place_of_birth_unknown);
        }
        mTvPlaceOfBirth.setText(placeOfBirth);
        String dateOfBirth = personDetails.getBirthDay();
        if (StringUtil.isEmpty(dateOfBirth, true)) {
            dateOfBirth = getString(R.string.personal_details_place_of_birth_unknown);
        }
        mTvDateOfBirth.setText(dateOfBirth);

        String dateOfDeath = personDetails.getDeathDay();
        if (StringUtil.isEmpty(dateOfDeath, true)) {
            mTvDateOfDeathTitle.setVisibility(View.GONE);
            mTvDateOfDeath.setVisibility(View.GONE);
        } else {
            mTvDateOfDeath.setText(dateOfDeath);
            mTvDateOfDeathTitle.setVisibility(View.VISIBLE);
            mTvDateOfDeath.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void displayPersonImages(List<com.mohammadsayed.celebrities.data.Photo> imagesPathList) {
        mPersonPhotosAdapter.setPhotos(imagesPathList);
    }

    @Override
    public void onPhotoSelected(Photo photo) {
        Intent intent = new Intent(getContext(), PhotoActivity.class);
        intent.putExtra(Constants.ExtrasKeys.PHOTO, photo);
        startActivity(intent);
    }
}
