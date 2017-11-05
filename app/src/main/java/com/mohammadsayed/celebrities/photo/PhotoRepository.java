package com.mohammadsayed.celebrities.photo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import com.mohammadsayed.architecture.core.Repository;
import com.mohammadsayed.architecture.network.CoreError;
import com.mohammadsayed.architecture.utils.CLog;
import com.mohammadsayed.architecture.utils.StringUtil;
import com.mohammadsayed.celebrities.AppUtility;
import com.mohammadsayed.celebrities.Constants;
import com.mohammadsayed.celebrities.R;
import com.mohammadsayed.celebrities.data.Photo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by mohammad on 1/25/17.
 */

public class PhotoRepository extends Repository<PhotoContract.PresenterCallback> implements PhotoContract.Repository<PhotoContract.PresenterCallback> {

    private Photo mPhoto;

    public PhotoRepository(Context context) {
        super(context);
    }

    @Override
    public void savePhoto(Photo photo) {
        this.mPhoto = photo;
    }

    @Override
    public void savePhotoLocally() {
        if (mPhoto == null) {
            return;
        }

        if (!StringUtil.isEmpty(mPhoto.getPhotoPath(), true)) {
            String fullUrl = AppUtility.getFullUrl(Constants.Photo.ORIGINAL_SIZE, mPhoto.getPhotoPath());
            imageDownload(fullUrl, mPhoto.getPhotoPath());
        }
    }

    public void imageDownload(String photoUrl, String path) {
        Picasso.with(getContext()).load(photoUrl)
                .into(getTarget(path));
    }

    //target to save
    private Target getTarget(final String url) {
        Target target = new Target() {

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + url);
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, ostream);
                            ostream.flush();
                            ostream.close();
                            addPhotoToGallery(file);
                            getPresenterCallback().onPhotoSavedLocally();
                        } catch (IOException e) {
                            CLog.e("IOException", e.getLocalizedMessage());
                            CoreError coreError = createPhotoError(R.string.personal_photo_error_saving);
                            getPresenterCallback().onError(coreError);
                        }
                    }
                }).start();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                CoreError coreError = createPhotoError(R.string.personal_photo_error_downloading);
                getPresenterCallback().onError(coreError);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }

    private CoreError createPhotoError(int stringId) {
        CoreError coreError = new CoreError();
        coreError.setStatusCode(Constants.ErrorCodes.DOWNLOAD_PHOTO_FAILED);
        if (getContext() != null) {
            coreError.setMessage(getContext().getString(stringId));
        }
        return coreError;
    }

    private void addPhotoToGallery(File file) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        getContext().sendBroadcast(mediaScanIntent);
    }
}
