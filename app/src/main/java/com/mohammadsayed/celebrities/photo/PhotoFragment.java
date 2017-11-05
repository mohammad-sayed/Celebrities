package com.mohammadsayed.celebrities.photo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.mohammadsayed.architecture.utils.PermissionUtil;
import com.mohammadsayed.architecture.utils.StringUtil;
import com.mohammadsayed.celebrities.AppUtility;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.bases.BaseInternetFragment;
import com.mohammadsayed.celebrities.data.Photo;
import com.squareup.picasso.Picasso;

public class PhotoFragment extends BaseInternetFragment<PhotoContract.Presenter>
        implements PhotoContract.ViewCallback<PhotoContract.Presenter> {


    private ImageView mIvPhoto;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_photo;
    }

    @Override
    public String getFragmentTag() {
        return getClass().getSimpleName();
    }

    @Override
    public PhotoContract.Presenter setUpPresenter() {
        return new PhotoPresenter(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_photo, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                getPresenter().savePhotoLocally();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void initializeViewsAndData(View view) {
        super.initializeViewsAndData(view);
        mIvPhoto = view.findViewById(R.id.iv_photo_photo);
    }

    public void setPhoto(Photo photo) {
        if (photo == null) {
            return;
        }
        getPresenter().savePhoto(photo);
        if (!StringUtil.isEmpty(photo.getPhotoPath(), true)) {
            String fullUrl = AppUtility.getFullUrl(Constants.Photo.ORIGINAL_SIZE, photo.getPhotoPath());
            Picasso.with(getContext()).load(fullUrl).error(R.drawable.img_not_found).into(mIvPhoto);
        } else {
            Picasso.with(getContext()).load(R.drawable.img_not_found).into(mIvPhoto);
        }
    }

    @Override
    public void requestPermission(String permission, int requestCode, int title, int description) {
        PermissionUtil.requestPermission(this, permission, requestCode, title, description);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        getPresenter().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
