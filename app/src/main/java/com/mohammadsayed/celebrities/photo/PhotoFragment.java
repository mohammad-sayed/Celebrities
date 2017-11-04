package com.mohammadsayed.celebrities.photo;

import android.view.View;
import android.widget.ImageView;

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
    protected void initializeViewsAndData(View view) {
        super.initializeViewsAndData(view);
        mIvPhoto = view.findViewById(R.id.iv_photo_photo);
    }

    public void setPhoto(Photo photo) {
        if (!StringUtil.isEmpty(photo.getPhotoPath(), true)) {
            String fullUrl = AppUtility.getFullUrl(Constants.Photo.ORIGINAL_SIZE, photo.getPhotoPath());
            Picasso.with(getContext()).load(fullUrl).error(R.drawable.img_not_found).into(mIvPhoto);
        } else {
            Picasso.with(getContext()).load(R.drawable.img_not_found).into(mIvPhoto);
        }
    }
}
