package com.mohammadsayed.celebrities.photo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;

import com.mohammadsayed.architecture.core.Presenter;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.architecture.utils.PermissionUtil;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.data.Photo;

/**
 * Created by mohammad on 1/15/17.
 */

public class PhotoPresenter extends Presenter<PhotoContract.ViewCallback, PhotoContract.Repository>
        implements PhotoContract.Presenter<PhotoContract.ViewCallback, PhotoContract.Repository>,
        PhotoContract.PresenterCallback {

    public PhotoPresenter(Context context) {
        super(context);
    }

    @Override
    protected PhotoContract.Repository setUpRepository() {
        return new PhotoRepository(getContext());
    }

    //Repository Callbacks
    @Override
    public void onError(CoreError error) {
        switch (error.getStatusCode()) {
            default:
                getViewCallback().showSnackBar(error.getMessage(), Snackbar.LENGTH_INDEFINITE, R.string.snackbar_dismiss, null);
        }
        getViewCallback().showLoadingIndicator(false);
    }

    @Override
    public void savePhoto(Photo photo) {
        getRepository().savePhoto(photo);
    }

    @Override
    public void savePhotoLocally() {
        if (!PermissionUtil.checkPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            getViewCallback().requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Constants.PermissionRequestCodes.WRITE_EXTERNAL_STORAGE, R.string.permission_write_external_title, R.string.permission_write_external_description);
            return;
        }
        getViewCallback().showLoadingIndicator(true);
        getRepository().savePhotoLocally();
    }

    @Override
    public void onPhotoSavedLocally() {
        getViewCallback().showSnackBar(R.string.personal_photo_photo_downloaded, Snackbar.LENGTH_LONG);
        getViewCallback().showLoadingIndicator(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constants.PermissionRequestCodes.WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    savePhotoLocally();
                } else {
                    getViewCallback().showSnackBar(R.string.permission_write_external_denied, Snackbar.LENGTH_INDEFINITE, R.string.snackbar_dismiss, null);
                }
                break;
            }
        }
    }
}
